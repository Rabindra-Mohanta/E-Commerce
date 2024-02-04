package alkusi.mahato.e_commerce.Views

import alkusi.mahato.e_commerce.Constants.MyConstants
import alkusi.mahato.e_commerce.R
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.google.firebase.storage.FirebaseStorage
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
    var firebaseStorage = FirebaseStorage.getInstance().getReference(MyConstants.IMAGES+image)
    firebaseStorage.downloadUrl.addOnSuccessListener {uri->
        Glide.with(this.context).load(uri).placeholder(R.color.gery).into(this)

    }

}
