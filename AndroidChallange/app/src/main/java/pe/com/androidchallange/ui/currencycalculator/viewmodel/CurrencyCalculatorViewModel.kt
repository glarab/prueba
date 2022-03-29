package pe.com.androidchallange.ui.currencycalculator.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import pe.com.androidchallange.ui.currencycalculator.model.CalculatorModel
import pe.com.androidchallange.data.CurrencyProvider
import pe.com.androidchallange.model.Currency

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
        calculatorModel.value?.receiveMoney = calculate(
            getDoubleToString(textString),
            calculatorModel.value?.currencyModelSend,
            calculatorModel.value?.currencyModelReceive
        )
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
        calculatorModel.value?.sendMoney = calculate(
            getDoubleToString(textString),
            calculatorModel.value?.currencyModelSend,
            calculatorModel.value?.currencyModelReceive
        )

        calculatorModel.postValue(calculatorModel.value)


    }

    private fun calculate(
        doubleToString: Double,
        currencyModelSend: Currency?,
        currencyModelReceive: Currency?
    ): String {

        var value =
            (doubleToString / currencyModelReceive!!.valueToOneEuro) * currencyModelSend!!.valueToOneEuro
        return value.toString()
    }

    fun changeCurrency() {
        var value = calculatorModel.value;
        if (value != null) {
            var sendTemp = value.currencyModelSend
            value.currencyModelSend = value.currencyModelReceive
            value.currencyModelReceive = sendTemp;
            value.sendMoney = value.receiveMoney
            calculatorModel.postValue(value!!)
        }
    }

    fun changeSendSelected(currency: Currency) {
        var value = calculatorModel.value
        value!!.currencyModelSend = currency
        calculatorModel.postValue(value!!)
    }

    fun changeReceiveSelected(currency: Currency) {
        var value = calculatorModel.value
        value!!.currencyModelReceive = currency
        calculatorModel.postValue(value!!)
    }

}


