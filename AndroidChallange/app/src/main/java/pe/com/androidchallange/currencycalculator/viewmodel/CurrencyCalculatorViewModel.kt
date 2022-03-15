package pe.com.androidchallange.currencycalculator.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import pe.com.androidchallange.currencycalculator.model.CalculatorModel
import pe.com.androidchallange.currencycalculator.model.CurrencyProvider

class CurrencyCalculatorViewModel : ViewModel() {

    var calculatorModel = MutableLiveData<CalculatorModel>()

    fun init() {
        calculatorModel.value = CalculatorModel(
            CurrencyProvider.getInitSend(),
            CurrencyProvider.getInitReceive(),
            "0.0",
            "0.0"
        )
    }

    fun changeValueSend(textString: String) {
        calculatorModel.value?.sendMoney = textString
        calculatorModel.value?.receiveMoney =
            (getDoubleToString(textString) * (calculatorModel.value?.currencyModelReceive?.purchase
                ?: 0.0)).toString()
        calculatorModel.postValue(calculatorModel.value)
    }

    private fun getDoubleToString(toDouble: String): Double {
        if (toDouble != "" && toDouble.isNotEmpty()) {
            return toDouble.toDouble()
        }
        return 0.0
    }

    fun changeValueReceive(textString: String) {
        calculatorModel.value?.receiveMoney = textString
        calculatorModel.value?.sendMoney =
            (getDoubleToString(textString) / (calculatorModel.value?.currencyModelReceive?.sale
                ?: 0.0)).toString()
        calculatorModel.postValue(calculatorModel.value)
    }

    fun changeCurrency() {
        var value = calculatorModel.value;
        if(value!=null){
            var sendTemp = value.currencyModelSend
            value.currencyModelSend  =value.currencyModelReceive
            value.currencyModelReceive=sendTemp;
            value.sendMoney=value.receiveMoney
            calculatorModel.postValue(value!!)
        }
    }

}


