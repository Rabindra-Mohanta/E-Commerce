package alkusi.mahato.e_commerce.screens

import alkusi.mahato.e_commerce.Constants.FileName
import alkusi.mahato.e_commerce.Constants.MyConstants
import alkusi.mahato.e_commerce.R
import alkusi.mahato.e_commerce.RoomDb.MyRoomDb
import alkusi.mahato.e_commerce.datahelper.SharedPrefHelper
import alkusi.mahato.e_commerce.screens.Home.Model.ElectronicsDataRes
import alkusi.mahato.e_commerce.screens.Home.Model.MaleFemaleDataRes
import android.content.Context
import android.widget.Toast
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.common.reflect.TypeToken
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class OrderedViewModel @Inject constructor(
    @ApplicationContext val context: Context,
    private val sharedPrefHelper: SharedPrefHelper, private val myRoomDb: MyRoomDb
) : BaseViewModel() {
    val listOfData = ArrayList<NormalData>()
    val orderedDataList = MutableLiveData<List<NormalData>>()
    var listOfOrderedData = ArrayList<OrderedDataRes>()
    var firebaseFirestore: FirebaseFirestore = FirebaseFirestore.getInstance()
    var isProgressbarVisible = ObservableBoolean(false)
    var isNodataFound = ObservableBoolean(false)
    var orderedTitleList = ArrayList<String>()
    //data from local
    val orderedDao = myRoomDb.getOrderedDao()

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
        getOrderedData()
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

     fun getOrderedData() {
        if (!isConnectionAvailable(context)) {
            viewModelScope.launch {  orderedDataList.value = orderedDao.getAllData()}

            showNoNetworkMsg(context)
            return
        }
        isProgressbarVisible.set(true)
        firebaseFirestore.collection(MyConstants.USERS)
            .document(sharedPrefHelper.getString(MyConstants.EMAIL).toString()).get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val result = task.result
                    if (result.get(MyConstants.Ordered) != null) {
                        orderedTitleList.clear()
                        orderedTitleList.addAll(result.get(MyConstants.Ordered) as List<String>)
                         val ordered = ArrayList<NormalData>()
                        for (i in listOfData.indices) {
                            val normalData = listOfData[i]
                            for (j in orderedTitleList.indices) {
                                val title = orderedTitleList[j]
                                if (normalData.title == title) {
                                    ordered.add(normalData)
                                }
                            }
                        }
                        viewModelScope.launch {
                            orderedDao.deleteAllData()
                            orderedDao.insertData(ordered)
                        }
                        isProgressbarVisible.set(false)
                        orderedDataList.value = ordered

                    }

                }
            }
    }

    fun cancelOrder(title:String?)
    {
        if (!isConnectionAvailable(context)) {
            showNoNetworkMsg(context)
            return
        }
        orderedTitleList.remove(title)
        firebaseFirestore.collection(MyConstants.USERS)
            .document(sharedPrefHelper.getString(MyConstants.EMAIL).toString())
            .update(MyConstants.Ordered, orderedTitleList)
            .addOnSuccessListener {
                Toast.makeText(context, context.getString(R.string.txt_success), Toast.LENGTH_SHORT)
                    .show()
                getOrderedData()

            }
    }

}