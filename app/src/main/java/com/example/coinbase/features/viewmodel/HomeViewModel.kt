package com.example.coinbase.features.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coinbase.base.BaseViewModel
import com.example.coinbase.features.usecase.HomeUseCase
import com.example.coinbase.model.Data
import com.example.coinbase.utils.ResponseApi
import kotlinx.coroutines.launch

class HomeViewModel(
    private val homeUseCase: HomeUseCase
) : BaseViewModel() {



    private val _onSuccesCoin: MutableLiveData<List<Data>> = MutableLiveData()

    val onSuccessCoin: LiveData<List<Data>>
        get() = _onSuccesCoin


    fun getCoinBase() {
        viewModelScope.launch {
            callApi(
                suspend { homeUseCase.getCoinBase() },
                onSuccess = {
                    val result = it as? List<*>
                    _onSuccesCoin.postValue(
                        result?.filterIsInstance<Data>()
                    )
                })
            }
        }
    }


