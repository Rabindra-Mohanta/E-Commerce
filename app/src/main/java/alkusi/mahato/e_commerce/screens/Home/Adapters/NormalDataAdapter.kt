package alkusi.mahato.e_commerce.screens.Home.Adapters

import alkusi.mahato.e_commerce.R
import alkusi.mahato.e_commerce.databinding.SingleShoppingItemBinding
import alkusi.mahato.e_commerce.screens.NormalData
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView

class NormalDataAdapter(private val dataList:List<NormalData>, private val onItemClick:(NormalData)->Unit):RecyclerView.Adapter<NormalDataAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val view = DataBindingUtil.inflate<SingleShoppingItemBinding>(LayoutInflater.from(parent.context),
            R.layout.single_shopping_item,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data1 = dataList[position]
        holder.binding.data = data1
        holder.binding.originalAmount.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
        holder.itemView.setOnClickListener {
            onItemClick.invoke(data1)
        }
    }
    inner class ViewHolder(val binding:SingleShoppingItemBinding):RecyclerView.ViewHolder(binding.root)
    {

    }
}