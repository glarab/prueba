package pe.com.androidchallange.ui.curriencies.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import pe.com.androidchallange.databinding.ActivityListCurrenciesBinding
import pe.com.androidchallange.model.Currency
import pe.com.androidchallange.ui.curriencies.CurrencyAdapter
import pe.com.androidchallange.ui.curriencies.viewmodel.ListCurrenciesViewModel


class ListCurrenciesActivity : AppCompatActivity(), CurrencyAdapter.OnCClickListener {
    private lateinit var mainAdapter: CurrencyAdapter

    private lateinit var binding: ActivityListCurrenciesBinding
    private val listCurrenciesViewModel: ListCurrenciesViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListCurrenciesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initEvents()
        initViews()
        listCurrenciesViewModel.init()

        listCurrenciesViewModel.listCurrenciesModel.observe(this) {
            mainAdapter.setList(it.currencies)
        }
    }

    private fun initViews() {
        mainAdapter = CurrencyAdapter(this, this)
        binding.recyclerviewCards.adapter = mainAdapter
        binding.recyclerviewCards.layoutManager = LinearLayoutManager(this)
    }

    private fun initEvents() {


    }

    override fun onCClick(currency: Currency, position: Int) {
        val returnIntent = Intent()
        returnIntent.putExtra("result", currency)
        setResult(RESULT_OK, returnIntent)
        finish()
    }


}