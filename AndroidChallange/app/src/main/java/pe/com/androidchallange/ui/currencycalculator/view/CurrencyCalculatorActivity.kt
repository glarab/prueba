package pe.com.androidchallange.ui.currencycalculator.view

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import pe.com.androidchallange.ui.currencycalculator.viewmodel.CurrencyCalculatorViewModel
import pe.com.androidchallange.databinding.ActivityCurrencyCalculatorBinding
import pe.com.androidchallange.model.Currency
import pe.com.androidchallange.ui.curriencies.view.ListCurrenciesActivity

class CurrencyCalculatorActivity : AppCompatActivity() {

    private val RECEIVE: Int = 2
    private val SEND: Int = 1
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

            binding.tvType.setText("Compra: ${calculatorModel.currencyModelReceive.valueToOneEuro} | Venta: ${calculatorModel.currencyModelReceive.valueToOneEuro}")


        })
        initEvents()
    }

    private fun initEvents() {
        binding.etSend.addTextChangedListener(textWatcher)
        binding.etReceive.addTextChangedListener(textWatcher)
        binding.fabChange.setOnClickListener { currencyCalculatorBinding.changeCurrency() }
        binding.etCurrencySend.setOnLongClickListener {
            callingActivitySelected(SEND)
            return@setOnLongClickListener true
        }
        binding.etCurrencyReceive.setOnLongClickListener {
            callingActivitySelected(RECEIVE)
            return@setOnLongClickListener true
        }
    }

    private fun callingActivitySelected(type: Int) {
        if (type == SEND)
            startForResultSend.launch(Intent(this, ListCurrenciesActivity::class.java))
        else if (type == RECEIVE) {
            startForResultReceive.launch(Intent(this, ListCurrenciesActivity::class.java))
        }
    }

    private val startForResultSend =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            if (result.resultCode == Activity.RESULT_OK) {
                val intent = result.data
                val currency: Currency = intent?.getSerializableExtra("result") as Currency
                currencyCalculatorBinding.calculatorModel.value?.currencyModelSend = currency
                // currencyCalculatorBinding.calculatorModel.
                currencyCalculatorBinding.changeSendSelected(currency)
            }
        }

    private val startForResultReceive =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            if (result.resultCode == Activity.RESULT_OK) {
                val intent = result.data
                val currency: Currency = intent?.getSerializableExtra("result") as Currency
                // currencyCalculatorBinding.calculatorModel.value?.currencyModelReceive = currency
                currencyCalculatorBinding.changeReceiveSelected(currency)
            }
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