package alkusi.mahato.e_commerce.screens.Home.Adapters

import alkusi.mahato.e_commerce.Constants.MyConstants
import alkusi.mahato.e_commerce.R
import alkusi.mahato.e_commerce.databinding.ItemMaleFemaleDataBinding
import alkusi.mahato.e_commerce.screens.Home.Model.MaleFemaleDataRes
import alkusi.mahato.e_commerce.screens.NormalData
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MaleFemaleDataAdapter(
    val data: MaleFemaleDataRes,
    private val onItemClick: (NormalData) -> Unit
) : RecyclerView.Adapter<MaleFemaleDataAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = DataBindingUtil.inflate<ItemMaleFemaleDataBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_male_female_data, parent, false
        )
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return 2
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (position == 0)
            holder.binding.categoryName = MyConstants.Traditional
        if (position == 1)
            holder.binding.categoryName = MyConstants.Western

        // set adapter
        holder.binding.recyclerView.setHasFixedSize(true)
        holder.binding.recyclerView.layoutManager = GridLayoutManager(
            holder.binding.recyclerView.context, 2,
            GridLayoutManager.VERTICAL, false
        )
        val adapter = NormalDataAdapter(
            if (position == 0) {
                data.traditional
            } else {
                data.western
            }, onItemClick
        )
        holder.binding.recyclerView.adapter = adapter
        adapter.notifyDataSetChanged()

    }

    inner class ViewHolder(val binding: ItemMaleFemaleDataBinding) :
        RecyclerView.ViewHolder(binding.root) {}

}