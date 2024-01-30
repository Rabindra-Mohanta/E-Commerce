/*
package alkusi.mahato.e_commerce.screens.Home.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.rabindra.R
import com.example.rabindra.constants.Constants
import com.example.rabindra.databinding.SingleShoppingItemBinding
import com.example.rabindra.screens.interfaces.OnItemFaveCartClick
import com.example.rabindra.screens.models.Items

class AdapterItems(
    private val list: ArrayList<Items>,
    private val categoryId: Int,
    private val listener: OnItemFaveCartClick
) : RecyclerView.Adapter<AdapterItems.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = SingleShoppingItemBinding.inflate(LayoutInflater.from(parent.context))
        return ViewHolder(binding)
    }


    override fun getItemCount(): Int {
        return list.size
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = list[position]
        holder.setData(data)
    }


    inner class ViewHolder(val binding: SingleShoppingItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun setData(data: Items) {
            Glide.with(binding.imgProduct.context).load(data.icon)
                .into(binding.imgProduct)
            binding.txtProductName.text = data.name
            binding.txtPrice.text = Constants.rupee + data.price.toString()
            setFavoriteImage(data.isFavorite, binding.imgFav.context)
            setClickListener(data)
        }


        //it will set image
        private fun setFavoriteImage(isFavorite: Boolean, context: Context) {
            if (isFavorite)
                binding.imgFav.setImageDrawable(
                    context.resources.getDrawable(
                        R.drawable.fav_red_icon,
                        null
                    )
                )
            else
                binding.imgFav.setImageDrawable(
                    context.resources.getDrawable(R.drawable.fav_normal_icon, null)
                )
        }

        //this is for clickListener
        private fun setClickListener(data: Items) {
            binding.btnAdd.setOnClickListener {
                data.isCart = !data.isCart
                data.productCount = 1;
                listener.onCartClick(categoryId, data)
            }

            binding.imgFav.setOnClickListener {
                if (data.isFavorite) {
                    val scaleUpAnimation =
                        AnimationUtils.loadAnimation(it.context, R.anim.animate_like_icon)
                    binding.imgFav.startAnimation(scaleUpAnimation)
                    binding.imgFav.setImageDrawable(
                        it.context.resources.getDrawable(
                            R.drawable.fav_normal_icon,
                            null
                        )
                    )
                } else {
                    val scaleUpAnimation =
                        AnimationUtils.loadAnimation(it.context, R.anim.animate_like_icon)
                    binding.imgFav.startAnimation(scaleUpAnimation)
                    binding.imgFav.setImageDrawable(
                        it.context.resources.getDrawable(
                            R.drawable.fav_red_icon,
                            null
                        )
                    )
                }
                data.isFavorite = !data.isFavorite
                listener.onFavClick(categoryId, data)
            }
        }

    }

}*/
