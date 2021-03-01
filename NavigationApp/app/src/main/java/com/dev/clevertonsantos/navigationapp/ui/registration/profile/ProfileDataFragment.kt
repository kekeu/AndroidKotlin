package com.dev.clevertonsantos.navigationapp.ui.registration.profile

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
import com.dev.clevertonsantos.navigationapp.ui.registration.RegistrationViewModel
import com.google.android.material.textfield.TextInputLayout

class ProfileDataFragment : Fragment() {

    private val registrationViewModel: RegistrationViewModel by activityViewModels()
    private lateinit var inputLayoutProfileDataName: TextInputLayout
    private lateinit var inputLayoutProfileDataBio: TextInputLayout
    private lateinit var inputProfileDataName: AppCompatEditText
    private lateinit var inputProfileDataBio: AppCompatEditText

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_profile_data, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        inputLayoutProfileDataName = view.findViewById(R.id.inputLayoutProfileDataName)
        inputLayoutProfileDataBio = view.findViewById(R.id.inputLayoutProfileDataBio)
        inputProfileDataName = view.findViewById(R.id.inputProfileDataName)
        inputProfileDataBio = view.findViewById(R.id.inputProfileDataBio)

        val validationsFields = initValidationFields()
        listenRegistrationViewModelEvents(validationsFields)

        val buttonProfileDataNext = view.findViewById<Button>(R.id.buttonProfileDataNext)
        buttonProfileDataNext.setOnClickListener {
            val name = inputProfileDataName.text.toString()
            val bio = inputProfileDataBio.text.toString()
            registrationViewModel.collectProfileData(name, bio)
        }

        inputProfileDataName.addTextChangedListener {
            inputLayoutProfileDataName.clearError()
        }

        inputProfileDataBio.addTextChangedListener {
            inputLayoutProfileDataBio.clearError()
        }

        registrationDeviceBackStackCallback()
    }

    fun initValidationFields() = mapOf(
            RegistrationViewModel.INPUT_NAME.first to inputLayoutProfileDataName,
            RegistrationViewModel.INPUT_BIO.first to inputLayoutProfileDataBio
    )

    private fun listenRegistrationViewModelEvents(validationsFields: Map<String, TextInputLayout>) {
        registrationViewModel.registrationStateEvent.observe(
                viewLifecycleOwner, { registrationState ->
            when (registrationState) {
                is RegistrationViewModel.RegistrationState.CollectCredentials -> {
                    val directions = ProfileDataFragmentDirections
                            .actionProfileDataFragmentToChooseCredentialsFragment(
                                    inputProfileDataName.text.toString())

                    findNavController().navigate(directions)
                }
                is RegistrationViewModel.RegistrationState.InvalidProfileData -> {
                    registrationState.fields.forEach { fieldError ->
                        validationsFields[fieldError.first]?.error = getString(fieldError.second)
                    }
                }
            }
        })
    }

    private fun registrationDeviceBackStackCallback() {
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            cancelRegistration()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        cancelRegistration()
        return true
    }

    private fun cancelRegistration() {
        registrationViewModel.userCancelledRegistration()
        findNavController().popBackStack(R.id.loginFragment, false)
    }
}