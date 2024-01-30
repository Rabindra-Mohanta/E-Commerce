/*
package alkusi.mahato.e_commerce.screens.Favorite.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.rabindra.R
import com.example.rabindra.constants.Constants
import com.example.rabindra.databinding.ItemFavoriteBinding
import com.example.rabindra.screens.interfaces.OnItemFaveCartClick
import com.example.rabindra.screens.models.Items

class AdapterFavorite(
    private val list: ArrayList<Pair<Items, Int>>,
    private val listener: OnItemFaveCartClick
) : RecyclerView.Adapter<AdapterFavorite.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemFavoriteBinding.inflate(LayoutInflater.from(parent.context))
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

    inner class ViewHolder(val binding: ItemFavoriteBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun setData(data: Items, id: Int) {
            binding.txtUnit.text = "" + Constants.one + " " + Constants.unit
            binding.txtPrice.text = Constants.rupee + data.price
            binding.txtProductName.text = data.name
            Glide.with(binding.imageView.context).load(data.icon).into(binding.imageView)
            setFavoriteImage(data.isFavorite, binding.imgLiked.context)
            setClickListener(data, id)
        }

        private fun setFavoriteImage(isFavorite: Boolean, context: Context) {
            if (isFavorite)
                binding.imgLiked.setImageDrawable(
                    context.resources.getDrawable(
                        R.drawable.fav_red_icon,
                        null
                    )
                )
            else
                binding.imgLiked.setImageDrawable(
                    context.resources.getDrawable(R.drawable.fav_normal_icon, null)
                )
        }

        private fun setClickListener(data: Items, categoryId: Int) {
            binding.btnAddCart.setOnClickListener {
                data.isCart = !data.isCart
                data.productCount = 1;
                val arrayList = ArrayList<Items>()
                list.forEach {
                    arrayList.add(it.first)
                }
                listener.onCartClick(categoryId, data)
            }

            binding.imgLiked.setOnClickListener {
                val scaleUpAnimation =
                    AnimationUtils.loadAnimation(it.context, R.anim.animate_like_icon)
                binding.imgLiked.startAnimation(scaleUpAnimation)

                data.isFavorite = !data.isFavorite
                binding.imgLiked.setImageDrawable(
                    it.context.resources.getDrawable(
                        R.drawable.fav_normal_icon,
                        null
                    )
                )
                listener.onFavClick(categoryId, data)
                list.removeAt(position)
                notifyItemRemoved(position)
            }
        }
    }
}
*/
