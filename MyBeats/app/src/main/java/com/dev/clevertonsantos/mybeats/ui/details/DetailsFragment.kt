package com.dev.clevertonsantos.mybeats.ui.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.dev.clevertonsantos.mybeats.R

class DetailsFragment : Fragment() {

    private val args: DetailsFragmentArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val textName = view.findViewById<TextView>(R.id.detailsName)
        val textConnection = view.findViewById<TextView>(R.id.detailsConectionValue)
        val textCompatibility = view.findViewById<TextView>(R.id.detailsCompatibilityValue)
        val textCharge = view.findViewById<TextView>(R.id.detailsChargeValue)
        val textAutonomy = view.findViewById<TextView>(R.id.detailsAutonomyValue)
        val textHeight = view.findViewById<TextView>(R.id.detailsHeightValue)
        val textCapture = view.findViewById<TextView>(R.id.detailsCaptureValue)
        val imageView = view.findViewById<ImageView>(R.id.detailsImage)

        context?.let { Glide.with(it).load(args.image).into(imageView) }
        textName.text = args.name
        textConnection.text = args.connection
        textCompatibility.text = args.compatibility
        textCharge.text = args.charge
        textAutonomy.text = args.autonomy
        textHeight.text = args.height
        textCapture.text = args.capture
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return true
    }

}