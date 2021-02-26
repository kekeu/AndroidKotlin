package com.dev.clevertonsantos.navigationapp.ui.start

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.dev.clevertonsantos.navigationapp.R
import com.dev.clevertonsantos.navigationapp.extensions.navigateWithAnim

class StartFragment : Fragment() {

    private lateinit var startNextButton: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_start, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        startNextButton = view.findViewById(R.id.startNextButton)

        startNextButton.setOnClickListener {
            findNavController().navigateWithAnim(R.id.action_startFragment_to_profileFragment)
        }
    }
}