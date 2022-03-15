package pe.com.androidchallange.currencycalculator.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import pe.com.androidchallange.R
import pe.com.androidchallange.currencycalculator.viewmodel.CurrencyCalculatorViewModel
import pe.com.androidchallange.databinding.ActivityCurrencyCalculatorBinding
import pe.com.androidchallange.databinding.ActivitySplashBinding

class CurrencyCalculatorActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCurrencyCalculatorBinding

    private val currencyCalculatorBinding: CurrencyCalculatorViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityCurrencyCalculatorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        currencyCalculatorBinding.init()

        currencyCalculatorBinding.calculatorModel.observe(this, Observer { calculatorModel ->
            binding.etCurrencyReceive.text = calculatorModel.currencyModelReceive.name
            binding.etCurrencySend.text = calculatorModel.currencyModelSend.name
            if (!calculatorModel.receiveMoney.equals(binding.etReceive.text.toString())) {
                binding.etReceive.setText(calculatorModel.receiveMoney)
            }
            if (!calculatorModel.sendMoney.equals(binding.etSend.text.toString())) {
                binding.etSend.setText(calculatorModel.sendMoney)
            }

            binding.tvType.setText("Compra: ${calculatorModel.currencyModelReceive.sale} | Venta: ${calculatorModel.currencyModelReceive.purchase}")


        })
        initEvents()
    }

    private fun initEvents() {
        binding.etSend.addTextChangedListener(textWatcher)
        binding.etReceive.addTextChangedListener(textWatcher)
        binding.fabChange.setOnClickListener{currencyCalculatorBinding.changeCurrency()}
    }

    private val textWatcher = object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {

        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            if (binding.etSend.isFocused) {
                currencyCalculatorBinding.changeValueSend(binding.etSend.text.toString());
            } else if (binding.etReceive.isFocused) {
                currencyCalculatorBinding.changeValueReceive(binding.etReceive.text.toString());
            }
        }
    }
}