package alkusi.mahato.e_commerce.Views

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("imageFromUrl")
fun ImageView.imageFromUrl(image: String)
{
    Glide.with(this.context).load(image).into(this)
}
