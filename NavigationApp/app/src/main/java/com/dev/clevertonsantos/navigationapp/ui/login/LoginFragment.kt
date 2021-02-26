package com.dev.clevertonsantos.navigationapp.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.activity.addCallback
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.dev.clevertonsantos.navigationapp.R
import com.dev.clevertonsantos.navigationapp.extensions.clearError
import com.dev.clevertonsantos.navigationapp.extensions.navigateWithAnim
import com.google.android.material.textfield.TextInputLayout

class LoginFragment : Fragment() {

    private lateinit var buttonLoginSignIn: Button
    private lateinit var buttonLoginSignUp: Button
    private lateinit var inputLoginUsername: AppCompatEditText
    private lateinit var inputLoginPassword: AppCompatEditText
    private lateinit var inputLayoutLoginUsername: TextInputLayout
    private lateinit var inputLayoutLoginPassword: TextInputLayout
    private val viewModel: LoginViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.login_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)

        buttonLoginSignIn = view.findViewById(R.id.buttonLoginSignIn)
        buttonLoginSignUp = view.findViewById(R.id.buttonLoginSignUp)
        inputLoginUsername = view.findViewById(R.id.inputLoginUsername)
        inputLoginPassword = view.findViewById(R.id.inputLoginPassword)
        inputLayoutLoginUsername = view.findViewById(R.id.inputLayoutLoginUsername)
        inputLayoutLoginPassword = view.findViewById(R.id.inputLayoutLoginPassword)

        viewModel.authenticationStateEvent.observe(viewLifecycleOwner, { authenticationSate ->
            when(authenticationSate) {
                is LoginViewModel.AuthenticationState.InvalidAuthentication -> {
                    val validationFields: Map<String, TextInputLayout> = initValidationFields()

                    authenticationSate.fields.forEach { fieldError ->
                        validationFields[fieldError.first]?.error = getString(fieldError.second)
                    }
                }
                is LoginViewModel.AuthenticationState.Authenticated -> {
                    findNavController().popBackStack()
                }
            }
        })

        buttonLoginSignIn.setOnClickListener {
            val username = inputLoginUsername.text.toString()
            val password = inputLoginPassword.text.toString()

            viewModel.authentication(username, password)
        }

        buttonLoginSignUp.setOnClickListener {
            findNavController().navigateWithAnim(R.id.action_loginFragment_to_nav_graph_registration)
        }

        inputLoginUsername.addTextChangedListener {
            inputLayoutLoginUsername.clearError()
        }

        inputLoginPassword.addTextChangedListener {
            inputLayoutLoginPassword.clearError()
        }

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            findNavController().popBackStack(R.id.startFragment, false)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        viewModel.refuseAuthentication()
        findNavController().popBackStack(R.id.startFragment, false)
        return true
    }

    private fun initValidationFields() = mapOf(
        LoginViewModel.INPUT_USERNAME.first to inputLayoutLoginUsername,
        LoginViewModel.INPUT_PASSWORD.first to inputLayoutLoginPassword
    )
}