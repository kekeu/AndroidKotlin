package com.dev.clevertonsantos.navigationapp.ui.registration.choosecredentials

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatTextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.dev.clevertonsantos.navigationapp.R

class ChooseCredentialsFragment : Fragment() {

    private val args: ChooseCredentialsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_choose_credentials, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val textChooseCredentialsName = view.findViewById<AppCompatTextView>(
                R.id.textChooseCredentialsName)
        textChooseCredentialsName.text = getString(R.string.choose_credentials_text_name, args.name)
    }
}