package com.dev.clevertonsantos.mybeats.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.ViewFlipper
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dev.clevertonsantos.mybeats.R
import com.dev.clevertonsantos.mybeats.data.model.Headphone
import com.dev.clevertonsantos.mybeats.data.repository.HeadphoneApiDataSource

class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel = HomeViewModel.ViewModelFactory(HeadphoneApiDataSource())
        .create(HomeViewModel::class.java)
    private val homeAdapter = HomeAdapter(::onItemClicked)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerBooks = view.findViewById<RecyclerView>(R.id.recyclerHeadphones)
        with(recyclerBooks) {
            layoutManager = LinearLayoutManager(activity,
                    RecyclerView.VERTICAL,false)
            setHasFixedSize(true)
            adapter = homeAdapter
        }

        viewModel.headphonesLiveData.observe(viewLifecycleOwner, {
            it?.let { headphones ->
                homeAdapter.addItens(headphones)
            }
        })
        viewModel.viewFlipperLiveData.observe(viewLifecycleOwner, {
            it?.let { viewFlipper ->
                val flipper = view.findViewById<ViewFlipper>(R.id.viewFlipper)
                flipper.displayedChild = viewFlipper.first
                viewFlipper.second?.let { errorMessage ->
                    view.findViewById<TextView>(R.id.error).text = errorMessage
                }
            }
        })
        viewModel.getHeadphones()
    }

    private fun onItemClicked(headphone: Headphone) {
        val valuesDirections = HomeFragmentDirections
                .actionHomeFragmentToDetailsFragment(height = headphone.height,
                        name = headphone.name, autonomy = headphone.autonomy,
                        capture = headphone.capture, charge = headphone.charge,
                        compatibility = headphone.compatibility, image = headphone.image,
                        connection = headphone.connection)
        findNavController().navigate(valuesDirections)
    }
}