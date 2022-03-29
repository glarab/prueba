package pe.com.androidchallange.ui.curriencies

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil.DiffResult.NO_POSITION
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import pe.com.androidchallange.base.BaseViewHolder
import pe.com.androidchallange.databinding.RowViewBinding
import pe.com.androidchallange.model.Currency


class CurrencyAdapter(
    private val context: Context,
    private val itemClickListener: OnCClickListener
) : RecyclerView.Adapter<BaseViewHolder<*>>() {

    private var externalRateList = listOf<Currency>()

    interface OnCClickListener {
        fun onCClick(currencyModel: Currency, position: Int)
    }

    fun setList(cList: List<Currency>) {
        this.externalRateList = cList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val itemBinding = RowViewBinding.inflate(LayoutInflater.from(context), parent, false)
        val holder = MainViewHolder(itemBinding)

        holder.itemView.setOnClickListener {
            val position = holder.adapterPosition.takeIf { it != NO_POSITION }
                ?: return@setOnClickListener

            itemClickListener.onCClick(externalRateList[position], position)
        }



        return holder
    }

    override fun getItemCount(): Int = externalRateList.size

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when (holder) {
            is MainViewHolder -> holder.bind(externalRateList[position])
        }
    }

    private inner class MainViewHolder(
        private val binding: RowViewBinding
    ) : BaseViewHolder<Currency>(binding.root) {
        override fun bind(item: Currency) = with(binding) {
            Glide.with(context)
                .load(item.flag)
                .centerCrop()
                .into(imgC)

            txtTitulo.text = item.name
            txtDescripcion.text = "1 EUR =${item.valueToOneEuro} ${item.symbol}"
        }
    }
}