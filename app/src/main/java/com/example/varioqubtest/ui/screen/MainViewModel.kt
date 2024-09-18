package com.example.varioqubtest.ui.screen

import android.util.Log
import androidx.lifecycle.ViewModel
import com.yandex.varioqub.config.FetchError
import com.yandex.varioqub.config.OnFetchCompleteListener
import com.yandex.varioqub.config.Varioqub
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainViewModel : ViewModel() {

    private val _isLoaded = MutableStateFlow(false)
    val isLoaded = _isLoaded.asStateFlow()

    private val _isNewColor = MutableStateFlow(false)
    val isNewColor = _isNewColor.asStateFlow()

    init {
        fetchRemoteConfig()
    }

    private fun fetchRemoteConfig() {
        Varioqub.fetchConfig(object : OnFetchCompleteListener {
            override fun onError(message: String, error: FetchError) {
                Log.d("fetchConfig message", message)
                _isLoaded.value = true
            }

            override fun onSuccess() {
                Varioqub.activateConfig() {
                    Log.i("VARIOQUB config activated ", Varioqub.getId())
                    _isLoaded.value = true
                    _isNewColor.value = Varioqub.getBoolean("isNewColor", false)
                }
            }
        })
    }
}