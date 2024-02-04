package alkusi.mahato.e_commerce.Views

import alkusi.mahato.e_commerce.R
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import de.hdodenhof.circleimageview.CircleImageView

@BindingAdapter("imageFromUrl")
fun ImageView.imageFromUrl(image: String)
{
    Glide.with(this.context).load(image).placeholder(R.color.gery).into(this)
}
@BindingAdapter("circleImageFromUrl")
fun CircleImageView.circleImageFromUrl(image: String?)
{
         //FROM FIREBASE
    image?.let {
        Glide.with(this.context).load(image.toString()).placeholder(R.color.gery).into(this)

    }
}
