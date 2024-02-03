package alkusi.mahato.e_commerce.screens

import alkusi.mahato.e_commerce.Constants.FileName
import alkusi.mahato.e_commerce.Constants.MyConstants
import alkusi.mahato.e_commerce.datahelper.SharedPrefHelper
import alkusi.mahato.e_commerce.screens.Home.Model.ElectronicsDataRes
import alkusi.mahato.e_commerce.screens.Home.Model.MaleFemaleDataRes
import android.content.Context
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.MutableLiveData
import com.google.common.reflect.TypeToken
import com.google.firebase.firestore.FirebaseFirestore
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltViewModel
class CardViewModel @Inject constructor(@ApplicationContext private val context:Context,private val sharedPrefHelper:SharedPrefHelper):BaseViewModel() {
    val listOfData = ArrayList<NormalData>()
    val cartDataList = MutableLiveData<List<NormalData>>()
    var listOfCartData = ArrayList<OrderedDataRes>()
    var firebaseFirestore: FirebaseFirestore = FirebaseFirestore.getInstance()
    var isProgressbarVisible = ObservableBoolean(false)
    var isNodataFound = ObservableBoolean(false)
    init {
        listOfData.clear()
        // this load all data
        listOfData.addAll(loadMaleJsonFromAssets().western)
        listOfData.addAll(loadMaleJsonFromAssets().traditional)
        //load male data
        listOfData.addAll(loadFemaleDataFromAssets().western)
        listOfData.addAll(loadFemaleDataFromAssets().traditional)
        //add electronics
        listOfData.addAll(loadElectronicsDataFromAssets().electronics)
        //get ordered data
        getCartData()
    }
    fun loadMaleJsonFromAssets(): MaleFemaleDataRes {
        val inputStream = context?.assets?.open(FileName.MALEDATA)
        val size: Int = inputStream!!.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()
        val json = String(buffer, Charsets.UTF_8)
        val type = object : TypeToken<MaleFemaleDataRes>() {}.type
        return Gson().fromJson(json, type)
    }

    fun loadFemaleDataFromAssets(): MaleFemaleDataRes {
        val inputStream = context?.assets?.open(FileName.FEMALEDATA)
        val size: Int = inputStream!!.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()
        val json = String(buffer, Charsets.UTF_8)
        val type = object : TypeToken<MaleFemaleDataRes>() {}.type
        return Gson().fromJson(json, type)
    }

    fun loadElectronicsDataFromAssets(): ElectronicsDataRes {
        val inputStream = context?.assets?.open(FileName.ELECTRONICSDATA)
        val size: Int = inputStream!!.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()
        val json = String(buffer, Charsets.UTF_8)
        val type = object : TypeToken<ElectronicsDataRes>() {}.type
        return Gson().fromJson(json, type)
    }
    private fun getCartData() {
        if (!isConnectionAvailable(context)) {
            showNoNetworkMsg(context)
            return
        }
        isProgressbarVisible.set(true)
        firebaseFirestore.collection(MyConstants.USERS)
            .document(sharedPrefHelper.getString(MyConstants.EMAIL).toString()).get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val result = task.result
                    if (result.get(MyConstants.CART) != null) {
                        val cartTitleList = ArrayList<String>()
                        cartTitleList.addAll(result.get(MyConstants.CART) as List<String>)
                        val cartData = ArrayList<NormalData>()
                        for (i in listOfData.indices) {
                            val normalData = listOfData[i]
                            for (j in cartTitleList.indices) {
                                val title = cartTitleList[j]
                                if (normalData.title == title) {
                                    cartData.add(normalData)
                                }
                            }
                        }
                        isProgressbarVisible.set(false)
                        cartDataList.value = cartData

                    }

                }
            }
    }

}