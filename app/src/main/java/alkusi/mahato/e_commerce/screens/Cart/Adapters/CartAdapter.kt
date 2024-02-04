package alkusi.mahato.e_commerce.screens.Cart.Adapters

import alkusi.mahato.e_commerce.R
import alkusi.mahato.e_commerce.databinding.ItemCartBinding
import alkusi.mahato.e_commerce.screens.NormalData
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView

class CartAdapter(private val dataList:List<NormalData>,private val onRemoveClick:(String)->Unit):
    RecyclerView.Adapter<CartAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = DataBindingUtil.inflate<ItemCartBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_cart,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data1 = dataList[position]
        holder.binding.data = data1
        holder.binding.originalAmount.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
        holder.binding.btnRemove.setOnClickListener {
            onRemoveClick.invoke(data1.title)
        }

    }
    inner class ViewHolder(val binding:ItemCartBinding):RecyclerView.ViewHolder(binding.root)
    {

    }
}