package com.rkhvstnv.splitbills

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MainViewModel: ViewModel() {
    private val _billValue: MutableStateFlow<String> = MutableStateFlow("")
    private val _perPersonBill: MutableStateFlow<Double> = MutableStateFlow(0.0)

    val billValue: StateFlow<String> get() = _billValue
    val perPersonBill: StateFlow<Double> get() = _perPersonBill

    fun updateBillValue(input: String){
        _billValue.value = input
    }
}