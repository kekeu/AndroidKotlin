package com.dev.clevertonsantos.mybeats.ui.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dev.clevertonsantos.mybeats.data.HeadphoneResult
import com.dev.clevertonsantos.mybeats.data.repository.HeadphoneRepository

class LoginViewModel(private val dataSource: HeadphoneRepository) : ViewModel() {

    val loginLiveData: MutableLiveData<Pair<Boolean, String?>> = MutableLiveData()

    fun login(email: String, senha: String) {
        dataSource.login(email, senha) { result ->
            when (result) {
                is HeadphoneResult.Success -> loginLiveData.value = Pair(true, null)
                is HeadphoneResult.ApiError -> loginLiveData.value = Pair(false, result.messageError)
                else -> loginLiveData.value = Pair(false, "Server Error")
            }
        }
    }

    class ViewModelFactory(val dataSource: HeadphoneRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
                return LoginViewModel(dataSource) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}