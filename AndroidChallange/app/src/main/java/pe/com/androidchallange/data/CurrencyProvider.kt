package pe.com.androidchallange.data

import pe.com.androidchallange.R
import pe.com.androidchallange.model.Currency

class CurrencyProvider {

    companion object {
        fun getInitSend(): Currency {
            return currency[0]
        }

        fun getInitReceive(): Currency {
            return currency[2]
        }

        fun getEuro(): Currency {
            return currency[1]
        }

        fun getList(): List<Currency> {
            return currency
        }

        private val currency = listOf<Currency>(
            Currency("Nuevo Sol", 4.130, R.drawable.flag_peru, "PEN"),
            Currency("European Union", 1.0000, R.drawable.flag_european, "EUR"),
            Currency("United States", 1.0963, R.drawable.flag_eeuu, "USD"),
            Currency("Japan", 110.02, R.drawable.flag_japon, "JPY"),
            Currency("United Kingdom", 0.8866, R.drawable.flag_unitedkingdom, "GBP"),
            Currency("Switzerland", 1.086, R.drawable.flag_switzerland, "CHF"),
            Currency("Canada", 1.4565, R.drawable.flag_canada, "CAD"),

            )
    }

}