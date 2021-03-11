package com.dev.clevertonsantos.mybeats.ui.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.ViewFlipper
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dev.clevertonsantos.mybeats.R
import com.dev.clevertonsantos.mybeats.extensions.navigateWithAnim

class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.headphonesLiveData.observe(viewLifecycleOwner, {
            it?.let { headphones ->
                val recyclerBooks = view.findViewById<RecyclerView>(R.id.recyclerHeadphones)
                with(recyclerBooks) {
                    layoutManager = LinearLayoutManager(activity,
                            RecyclerView.VERTICAL,false)
                    setHasFixedSize(true)
                    adapter = HomeAdapter(headphones) { book ->
                        findNavController().navigateWithAnim(R.id.action_homeFragment_to_detailsFragment)
                    }
                }
            }
        })
        viewModel.viewFlipperLiveData.observe(viewLifecycleOwner, {
            it?.let { viewFlipper ->
                val flipper = view.findViewById<ViewFlipper>(R.id.viewFlipper)
                flipper.displayedChild = viewFlipper.first
                viewFlipper.second?.let { errorMessaResId ->
                    view.findViewById<TextView>(R.id.error).text = getString(errorMessaResId)
                }
            }
        })
        viewModel.getHeadphones()
    }
}