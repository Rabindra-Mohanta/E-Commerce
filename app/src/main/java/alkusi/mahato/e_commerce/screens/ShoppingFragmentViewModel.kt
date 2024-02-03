package alkusi.mahato.e_commerce.screens

import alkusi.mahato.e_commerce.R
import android.app.Dialog
import android.content.Context
import android.widget.Toast
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.core.View
import com.saadahmedsoft.popupdialog.PopupDialog
import com.saadahmedsoft.popupdialog.Styles
import com.saadahmedsoft.popupdialog.listener.OnDialogButtonClickListener
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject


@HiltViewModel
class ShoppingFragmentViewModel @Inject constructor(@ApplicationContext private val context: Context) : BaseViewModel() {

    var normalData = ObservableField<NormalData>()
    var itemCount = MutableLiveData<Int>(1)
    var productOriginalPrice = MutableLiveData<Long>(0)
    var productOfferPrice = MutableLiveData<Long>(0)
    var isEnableCartBtn = ObservableBoolean(true)
    var isEnableBuyNowBtn = ObservableBoolean(true);

    fun onIncrementClick() {
        itemCount.value?.let {
            if (it > 0) {
                var count = it
                itemCount.value = it + 1
                //set price

                productOriginalPrice.value =
                    normalData.get()?.originalPrice?.toLong()?.times((it + 1))
                productOfferPrice.value = normalData.get()?.offerPrice?.toLong()?.times((it + 1))

            }
        }
    }

    fun onDecrementClick() {
        itemCount.value?.let {
            if (it > 1) {
                itemCount.value = it - 1
                //set price
                productOriginalPrice.value =
                    normalData.get()?.originalPrice?.toLong()?.times((it - 1))
                productOfferPrice.value = normalData.get()?.offerPrice?.toLong()?.times((it - 1))
            }
        }
    }




}