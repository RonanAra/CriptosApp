package com.example.coinbase.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel<State>: ViewModel() {

    protected val _state: MutableLiveData<State> = MutableLiveData()
    val state: LiveData<State> get() = _state
}