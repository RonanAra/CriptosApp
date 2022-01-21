package com.example.coinbase.base

import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import com.example.coinbase.utils.Command

abstract class BaseFragment: Fragment() {
    abstract var command: MutableLiveData<Command>
}