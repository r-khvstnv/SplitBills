package com.rkhvstnv.splitbills.main

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class MainViewModel: ViewModel() {
    private val _billValue: MutableStateFlow<String> = MutableStateFlow("")
    private val _perPersonBill: MutableStateFlow<Double> = MutableStateFlow(0.0)
    private val _personQuantity: MutableStateFlow<Int> = MutableStateFlow(1)
    private val _tipsPercent: MutableStateFlow<Float> = MutableStateFlow(0f)

    val billValue: StateFlow<String> get() = _billValue
    val perPersonBill: StateFlow<Double> get() = _perPersonBill
    val personQuantity: StateFlow<Int> get() = _personQuantity
    val tipsPercent: StateFlow<Float> get() = _tipsPercent

    fun updateBillValueAndCalculate(input: String){
        _billValue.value = input
        updatePerPersonBill()
    }

    fun increasePersonQuantity(){
        _personQuantity.value++
        updatePerPersonBill()
    }

    fun decreasePersonQuantity(){
        if (_personQuantity.value > 1){
            _personQuantity.value--
            updatePerPersonBill()
        }
    }

    fun updateTipsPercent(input: Float){
        _tipsPercent.value = input

        updatePerPersonBill()
    }

    fun updatePerPersonBill(){
        val bill = _billValue.value.toDouble()
        val totalBill: Double = bill + (bill * _tipsPercent.value)

        _perPersonBill.value = totalBill / _personQuantity.value
    }
}