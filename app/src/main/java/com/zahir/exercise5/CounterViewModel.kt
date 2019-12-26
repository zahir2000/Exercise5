package com.zahir.exercise5

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CounterViewModel: ViewModel() {
    private val _up = MutableLiveData<Int>()
    val up: LiveData<Int>
        get() = _up

    private val _down = MutableLiveData<Int>()
    val down: LiveData<Int>
        get() = _down

    init {
        _up.value = 0
        _down.value = 0
    }

    fun onUp(){
        _up.value = (up.value)?.plus(1)
    }

    fun onDown(){
        _down.value = (down.value)?.plus(1)
    }

    fun setUp(newUp: Int){
        _up.value = newUp
    }

    fun setDown(newDown: Int){
        _down.value = newDown
    }

    fun getUp() : Int?{
        return _up.value
    }

    fun getDown() : Int?{
        return _down.value
    }

    fun onClear(){
        _down.value = 0
        _up.value = 0
    }
}