package com.dev.clevertonsantos.mybeats.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dev.clevertonsantos.mybeats.data.HeadphoneResult
import com.dev.clevertonsantos.mybeats.data.model.Headphone
import com.dev.clevertonsantos.mybeats.data.repository.HeadphoneRepository

class HomeViewModel(val dataSource: HeadphoneRepository) : ViewModel() {

    val headphonesLiveData: MutableLiveData<List<Headphone>> = MutableLiveData()
    val viewFlipperLiveData: MutableLiveData<Pair<Int, String?>> = MutableLiveData()

    fun getHeadphones() {
        viewFlipperLiveData.value = Pair(VIEW_FLIPPER_LOADING, null)
        dataSource.getHeadphones {
            when (it) {
                is HeadphoneResult.Success -> {
                    headphonesLiveData.value = it.headphones
                    viewFlipperLiveData.value = Pair(VIEW_FLIPPER_HEADPHONES, null)
                }
                is HeadphoneResult.ApiError -> viewFlipperLiveData.value = Pair(
                    VIEW_FLIPPER_ERROR, it.messageError)
                else -> viewFlipperLiveData.value = Pair(VIEW_FLIPPER_ERROR, "Server Error")
            }
        }
    }

    companion object {
        private const val VIEW_FLIPPER_LOADING = 0
        private const val VIEW_FLIPPER_HEADPHONES = 1
        private const val VIEW_FLIPPER_ERROR = 2
    }

    class ViewModelFactory(val dataSource: HeadphoneRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
                return HomeViewModel(dataSource) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}