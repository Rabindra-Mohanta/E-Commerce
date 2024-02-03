package alkusi.mahato.e_commerce.screens

import alkusi.mahato.e_commerce.Constants.FileName
import alkusi.mahato.e_commerce.screens.Home.Model.ElectronicsDataRes
import alkusi.mahato.e_commerce.screens.Home.Model.MaleFemaleDataRes
import android.content.Context
import androidx.databinding.ObservableBoolean
import com.google.common.reflect.TypeToken
import com.google.firebase.firestore.FirebaseFirestore
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(@ApplicationContext val context: Context) :
    BaseViewModel() {
    var firebaseFirestore: FirebaseFirestore = FirebaseFirestore.getInstance()
    var isProgressbarVisible = ObservableBoolean(false)
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
}