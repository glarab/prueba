package pe.com.androidchallange.currencycalculator.model

class CurrencyProvider {

    companion object {
        fun getInitSend(): CurrencyModel {
            return currency[0]
        }

        fun getInitReceive(): CurrencyModel {
            return currency[1]
        }

        private val currency = listOf<CurrencyModel>(
            CurrencyModel("Sol", 3.7, 3.6),
            CurrencyModel("Dolar", String.format("%.3f", 1 / 3.6).toDouble(), String.format("%.3f", 1 / 3.7).toDouble())
        )
    }

}