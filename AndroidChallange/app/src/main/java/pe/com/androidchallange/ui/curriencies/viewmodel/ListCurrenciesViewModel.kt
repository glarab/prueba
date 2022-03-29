package pe.com.androidchallange.ui.curriencies.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import pe.com.androidchallange.data.CurrencyProvider
import pe.com.androidchallange.ui.curriencies.model.ListCurrenciesModel

class ListCurrenciesViewModel : ViewModel() {

    var listCurrenciesModel = MutableLiveData<ListCurrenciesModel>()
    fun init() {
        listCurrenciesModel.value = ListCurrenciesModel(
            CurrencyProvider.getList(),
            null
        )
    }

}