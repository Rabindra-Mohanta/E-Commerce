/*
package alkusi.mahato.e_commerce.screens.Home.Adapters
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.rabindra.databinding.ItemHomeBinding
import com.example.rabindra.screens.interfaces.OnItemFaveCartClick
import com.example.rabindra.screens.models.Categories

class HomeAdapter(
    private val list: ArrayList<Categories>,
    private val listener: OnItemFaveCartClick
) : RecyclerView.Adapter<HomeAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemHomeBinding.inflate(LayoutInflater.from(parent.context))
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.setData(item)

    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class ViewHolder(val binding: ItemHomeBinding) : RecyclerView.ViewHolder(binding.root) {
        fun setData(data: Categories) {
            binding.txtCategory.text = data.name
            val adapter = AdapterItems(data.items, data.id, listener)
            binding.recyclerView.adapter = adapter
            adapter.notifyDataSetChanged()
        }
    }

}*/
