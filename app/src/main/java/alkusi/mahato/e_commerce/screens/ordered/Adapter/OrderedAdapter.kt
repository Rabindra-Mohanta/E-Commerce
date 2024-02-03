package alkusi.mahato.e_commerce.screens.ordered.Adapter

import alkusi.mahato.e_commerce.R
import alkusi.mahato.e_commerce.databinding.OrderedItemBinding
import alkusi.mahato.e_commerce.screens.NormalData
import alkusi.mahato.e_commerce.screens.OrderedDataRes
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView

class OrderedAdapter(val dataList:List<NormalData>,val onCancelClick:(String)->Unit):RecyclerView.Adapter<OrderedAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = DataBindingUtil.inflate<OrderedItemBinding>(LayoutInflater.from(parent.context),
            R.layout.ordered_item,parent,false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data1 = dataList[position]
        holder.binding.data = data1
        holder.binding.originalAmount.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG

    }
    inner class ViewHolder(val binding:OrderedItemBinding):RecyclerView.ViewHolder(binding.root)
    {

    }
}