package alkusi.mahato.e_commerce.screens.Cart.Adapters
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import com.bumptech.glide.Glide
import com.example.rabindra.constants.Constants
import com.example.rabindra.databinding.ItemCartBinding
import com.example.rabindra.screens.dao.ProductDao
import com.example.rabindra.screens.models.Items
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class AdapterCart(
    private val list: ArrayList<Pair<Items, Int>>,
    private val productDao: ProductDao, private val onItemCountChange: () -> Unit
) : RecyclerView.Adapter<AdapterCart.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemCartBinding.inflate(LayoutInflater.from(parent.context))
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = list[position].first
        val id = list[position].second
        holder.setData(data, id)
    }

    inner class ViewHolder(val binding: ItemCartBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun setData(data: Items, id: Int) {
            binding.txtUnit.text =
                Constants.rupee + data.price + "/" + Constants.one + " " + Constants.unit
            binding.txtPrice.text = Constants.rupee + data.price
            binding.txtProductName.text = data.name
            Glide.with(binding.imageView.context).load(data.icon).into(binding.imageView)
            binding.txtPrice.text = Constants.rupee + (data.price * data.productCount)
            binding.txtCount.text = data.productCount.toString()
            setClickListener(data, id)
        }


        //click listener
        private fun setClickListener(data: Items, categoryId: Int) {
            binding.btnPlus.setOnClickListener {
                data.productCount = data.productCount + 1;
                binding.txtCount.text = data.productCount.toString()
                binding.txtPrice.text = Constants.rupee + (data.price * data.productCount)
                updateDataInRoom(data, categoryId)
            }
            binding.btnMinus.setOnClickListener {
                if (data.productCount > 1) {
                    data.productCount = data.productCount - 1;
                    binding.txtCount.text = data.productCount.toString()
                    binding.txtPrice.text = Constants.rupee + (data.price * data.productCount)
                    updateDataInRoom(data, categoryId)

                }
            }
        }


        //update data in room db
        private fun updateDataInRoom(data: Items, categoryId: Int) {
            GlobalScope.launch {
                val category = productDao.getDataById(categoryId)
                var position: Int = 0;
                for (i in category.items.indices) {
                    if (category.items[i].id == data.id) {
                        position = i;
                        continue
                    }
                }
                category.items.removeAt(position)
                category.items.add(position, data)
                productDao.updateData(category)
                onItemCountChange()
            }

        }

    }

}