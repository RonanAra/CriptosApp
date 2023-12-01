package com.example.coinbase.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.coinbase.data.models.response.Data
import com.example.coinbase.domain.repository.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: HomeRepository
) : ViewModel() {

    private val _listCoins: MutableLiveData<List<Data>> = MutableLiveData()

    val listCoins: LiveData<List<Data>>
        get() = _listCoins

    fun getCoinBase() {
        viewModelScope.launch {
           _listCoins.value = repository.getCoins()
        }
    }
    fun filterList(text: String) {
        viewModelScope.launch {
            _listCoins.value = repository.filterList(text)
        }
    }
}