package com.dev.clevertonsantos.mybeats.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dev.clevertonsantos.mybeats.data.model.Headphone

class HomeViewModel : ViewModel() {

    val headphonesLiveData: MutableLiveData<List<Headphone>> = MutableLiveData()
    val viewFlipperLiveData: MutableLiveData<Pair<Int, Int?>> = MutableLiveData()

    fun getHeadphones() {
        headphonesLiveData.value = listOf(
                Headphone("Fone Modelo 02", "4.6", "86 Reviews", "R$ 20,50"),
                Headphone("Fone Modelo 02", "4.6", "86 Reviews", "R$ 20,50"),
                Headphone("Fone Modelo 02", "4.6", "86 Reviews", "R$ 20,50"),
                Headphone("Fone Modelo 02", "4.6", "86 Reviews", "R$ 20,50"),
                Headphone("Fone Modelo 02", "4.6", "86 Reviews", "R$ 20,50"),
                Headphone("Fone Modelo 02", "4.6", "86 Reviews", "R$ 20,50"),
                Headphone("Fone Modelo 02", "4.6", "86 Reviews", "R$ 20,50"),
                Headphone("Fone Modelo 02", "4.6", "86 Reviews", "R$ 20,50"),
                Headphone("Fone Modelo 02", "4.6", "86 Reviews", "R$ 20,50"),
                Headphone("Fone Modelo 02", "4.6", "86 Reviews", "R$ 20,50"),
        )
        viewFlipperLiveData.value = Pair(VIEW_FLIPPER_BOOKS, null)
    }

    companion object {
        private const val VIEW_FLIPPER_BOOKS = 1
        private const val VIEW_FLIPPER_ERROR = 2
    }
}