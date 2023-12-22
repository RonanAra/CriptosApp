package com.example.coinbase.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coinbase.data.models.response.CoinResponse
import com.example.coinbase.domain.repository.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: HomeRepository
) : ViewModel() {

    private val _listCoins: MutableLiveData<List<CoinResponse>> = MutableLiveData()
    val listCoins: LiveData<List<CoinResponse>> get() = _listCoins

    private var coins: List<CoinResponse> = listOf()

    fun getCoins() {
        viewModelScope.launch {
            val coinsResponse = repository.getCoins()
           _listCoins.value = coinsResponse
            coins = coinsResponse
        }
    }
    fun filterList(text: String) {
       val coinsFiltered = coins.filter {
           it.name.lowercase().contains(text.lowercase())
       }
        _listCoins.value = coinsFiltered
    }
}