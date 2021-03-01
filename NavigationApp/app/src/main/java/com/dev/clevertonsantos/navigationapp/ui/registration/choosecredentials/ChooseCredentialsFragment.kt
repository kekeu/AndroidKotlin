package com.dev.clevertonsantos.navigationapp.ui.registration.choosecredentials

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.activity.addCallback
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.dev.clevertonsantos.navigationapp.R
import com.dev.clevertonsantos.navigationapp.extensions.clearError
import com.dev.clevertonsantos.navigationapp.ui.login.LoginViewModel
import com.dev.clevertonsantos.navigationapp.ui.registration.RegistrationViewModel
import com.google.android.material.textfield.TextInputLayout

class ChooseCredentialsFragment : Fragment() {

    private val loginViewModel: LoginViewModel by activityViewModels()
    private val registrationViewModel: RegistrationViewModel by activityViewModels()

    private lateinit var inputLayoutChooseCredentialsUsername: TextInputLayout
    private lateinit var inputLayoutChooseCredentialsPassword: TextInputLayout
    private lateinit var inputChooseCredentialsUsername:  AppCompatEditText
    private lateinit var inputChooseCredentialsPassword:  AppCompatEditText
    private lateinit var buttonChooseCredentialsNext: Button

    private val args: ChooseCredentialsFragmentArgs by navArgs()

    private val navController: NavController by lazy {
        findNavController()
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_choose_credentials, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)

        val textChooseCredentialsName = view.findViewById<AppCompatTextView>(
                R.id.textChooseCredentialsName)
        inputLayoutChooseCredentialsUsername = view.findViewById(
                R.id.inputLayoutChooseCredentialsUsername)
        inputLayoutChooseCredentialsPassword = view.findViewById(
                R.id.inputLayoutChooseCredentialsPassword)
        inputChooseCredentialsUsername = view.findViewById(
                R.id.inputChooseCredentialsUsername)
        inputChooseCredentialsPassword = view.findViewById(
                R.id.inputChooseCredentialsPassword)
        buttonChooseCredentialsNext = view.findViewById(
                R.id.buttonChooseCredentialsNext)

        textChooseCredentialsName.text = getString(R.string.choose_credentials_text_name, args.name)

        val invalidFields = initValidationFields()
        listenToRegistrationStateEvent(invalidFields)
        registerViewListeners()
        registerDeviceBackStack()
    }

    private fun initValidationFields() = mapOf(
            RegistrationViewModel.INPUT_USERNAME.first to inputLayoutChooseCredentialsUsername,
            RegistrationViewModel.INPUT_PASSWORD.first to inputLayoutChooseCredentialsPassword
    )

    private fun listenToRegistrationStateEvent(validationFields: Map<String, TextInputLayout>) {
        registrationViewModel.registrationStateEvent.observe(
                viewLifecycleOwner, { registrationState ->
            when (registrationState) {
                is RegistrationViewModel.RegistrationState.RegistrationCompleted -> {
                    val token = registrationViewModel.authToken
                    val username = inputChooseCredentialsUsername.text.toString()

                    loginViewModel.authentication(username, token)
                    navController.popBackStack(R.id.profileFragment, false)
                }
                is RegistrationViewModel.RegistrationState.InvalidCredentials -> {
                    registrationState.fields.forEach { fieldError ->
                        validationFields[fieldError.first]?.error = getString(fieldError.second)
                    }
                }
            }
        })
    }

    private fun registerViewListeners() {
        buttonChooseCredentialsNext.setOnClickListener {
            val username = inputChooseCredentialsUsername.text.toString()
            val password = inputChooseCredentialsPassword.text.toString()

            registrationViewModel.createCredentials(username, password)
        }

        inputChooseCredentialsUsername.addTextChangedListener {
            inputLayoutChooseCredentialsUsername.clearError()
        }

        inputChooseCredentialsPassword.addTextChangedListener {
            inputLayoutChooseCredentialsPassword.clearError()
        }
    }

    private fun registerDeviceBackStack() {
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            cancelRegistration()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        cancelRegistration()
        return super.onOptionsItemSelected(item)
    }

    private fun cancelRegistration() {
        registrationViewModel.userCancelledRegistration()
        navController.popBackStack(R.id.loginFragment, false)
    }
}