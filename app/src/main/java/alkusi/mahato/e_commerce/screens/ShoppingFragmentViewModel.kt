package alkusi.mahato.e_commerce.screens

import alkusi.mahato.e_commerce.Constants.MyConstants
import alkusi.mahato.e_commerce.R
import alkusi.mahato.e_commerce.datahelper.SharedPrefHelper
import android.content.Context
import android.widget.Toast
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import java.util.ArrayList
import javax.inject.Inject


@HiltViewModel
class ShoppingFragmentViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val sharedPrefHelper: SharedPrefHelper
) : BaseViewModel() {

    var normalData = ObservableField<NormalData>()
    var itemCount = MutableLiveData<Int>(1)
    var productOriginalPrice = MutableLiveData<Long>(0)
    var productOfferPrice = MutableLiveData<Long>(0)
    var isEnableCartBtn = ObservableBoolean(true)
    var isEnableBuyNowBtn = ObservableBoolean(true)
    var firebaseFirestore: FirebaseFirestore = FirebaseFirestore.getInstance()
    val allOrderedList = ArrayList<String>()
    val allCartList = ArrayList<String>()

    init {
        //get data
        getData()
    }

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


    fun callBuyDataApi(itemTitle: String) {
        if (!isConnectionAvailable(context)) {
            showNoNetworkMsg(context)
            return
        }
        allOrderedList.add(itemTitle)
        firebaseFirestore.collection(MyConstants.USERS)
            .document(sharedPrefHelper.getString(MyConstants.EMAIL).toString())
            .update(MyConstants.Ordered, allOrderedList)
            .addOnSuccessListener {
                Toast.makeText(context, context.getString(R.string.txt_success), Toast.LENGTH_SHORT)
                    .show()
                isEnableBuyNowBtn.set(false)

            }
    }

    fun callCartApi(itemTitle: String) {
        if (!isConnectionAvailable(context)) {
            showNoNetworkMsg(context)
            return
        }
        allCartList.add(itemTitle)
        firebaseFirestore.collection(MyConstants.USERS)
            .document(sharedPrefHelper.getString(MyConstants.EMAIL).toString())
            .update(MyConstants.CART, allCartList)
            .addOnSuccessListener {
                Toast.makeText(context, context.getString(R.string.txt_success), Toast.LENGTH_SHORT)
                    .show()
                isEnableCartBtn.set(false)
            }
    }

    private fun getData() {
        if(!isConnectionAvailable(context))
        {
            showNoNetworkMsg(context)
            return
        }
        firebaseFirestore.collection(MyConstants.USERS)
            .document(sharedPrefHelper.getString(MyConstants.EMAIL).toString()).get()
            .addOnCompleteListener(object : OnCompleteListener<DocumentSnapshot> {
                override fun onComplete(task: Task<DocumentSnapshot>) {
                    if (task.isSuccessful) {
                        val result = task.result
                        allOrderedList.clear()
                        allCartList.clear()
                        if (result.get(MyConstants.Ordered) != null) {
                            allOrderedList.addAll(result.get(MyConstants.Ordered) as List<String>)
                        }
                        if (result.get(MyConstants.CART) != null) {
                            allCartList.addAll(result.get(MyConstants.CART) as List<String>)

                        }

                        for(i in allOrderedList.indices)
                        {
                            val orderData = allOrderedList[i]
                            if(orderData==normalData.get()?.title)
                            {
                                isEnableBuyNowBtn.set(false)
                                break
                            }
                        }

                        for(i in allCartList.indices)
                        {
                            val cartList = allCartList[i]
                            if(cartList==normalData.get()?.title)
                            {
                                isEnableCartBtn.set(false)
                            }
                        }
                    }
                }

            })

    }

}