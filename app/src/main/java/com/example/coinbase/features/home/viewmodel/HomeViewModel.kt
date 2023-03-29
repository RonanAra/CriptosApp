package com.example.coinbase.features.home.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coinbase.features.home.usecase.HomeUseCase
import com.example.coinbase.model.Data
import com.example.coinbase.utils.ResponseApi
import kotlinx.coroutines.launch

class HomeViewModel(
    private val homeUseCase: HomeUseCase
) : ViewModel() {

    private val _onSuccesCoin: MutableLiveData<List<Data>> = MutableLiveData()

    val onSuccessCoin: LiveData<List<Data>>
        get() = _onSuccesCoin

    private val _onErrorCoin: MutableLiveData<Boolean> = MutableLiveData()

    val onErrorCoin: LiveData<Boolean>
        get() = _onErrorCoin

    fun getCoinBase() {
        viewModelScope.launch {
            when (val responseApi = homeUseCase.getCoinBase()) {
                is ResponseApi.Success -> {
                    val result = responseApi.data as? List<*>
                    _onSuccesCoin.postValue(
                        result?.filterIsInstance<Data>()
                    )
                }
                is ResponseApi.Error -> {
                    _onErrorCoin.postValue(true)
                }
            }
        }
    }
}