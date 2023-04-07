package com.profjpbaugh.viewmodeldemo.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    private val rate = 0.74f
    private var dollarText = ""
    //private var result : Float = 0f
    private var result : MutableLiveData<Float> = MutableLiveData()

    fun setAmount(value : String) {
        dollarText = value
        //result = value.toFloat()
        result.setValue(value.toFloat() * rate)
    }//end setAmount

    fun getResult() : MutableLiveData<Float> {
        return result
    }//end getResult
}