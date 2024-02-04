package alkusi.mahato.e_commerce.screens

import alkusi.mahato.e_commerce.Constants.MyConstants
import alkusi.mahato.e_commerce.R
import alkusi.mahato.e_commerce.RoomDb.MyRoomDb
import alkusi.mahato.e_commerce.datahelper.SharedPrefHelper
import android.content.Context
import android.net.Uri
import android.util.Log
import android.widget.Toast
import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val sharedPrefHelper: SharedPrefHelper,
    private val myRoomDb: MyRoomDb
) : BaseViewModel() {
    var firebaseFirestore: FirebaseFirestore = FirebaseFirestore.getInstance()
    var isProgressbarVisible = ObservableBoolean(false)
    var profileDataRes = ObservableField<ProfileDataRes>()
    var isUpdatedProfile = MutableLiveData<Boolean>(false)
    val profileDao = myRoomDb.getProfileDao()
    var imageUrl = MutableLiveData<String>()

    init {
        getProfileData()
    }


    private fun getProfileData() {
        if (!isConnectionAvailable(context)) {
            viewModelScope.launch {
                profileDataRes.set(profileDao.getAllData())
            }
            showNoNetworkMsg(context)
            return
        }
        isProgressbarVisible.set(true)
        firebaseFirestore.collection(MyConstants.USERS)
            .document(sharedPrefHelper.getString(MyConstants.EMAIL).toString()).get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    val result = task.result
                    val res = ProfileDataRes(
                        result.get(MyConstants.PROFILE).toString(),
                        result.get(MyConstants.NAME).toString(),
                        result.get(MyConstants.CONTACT).toString(),
                        result.get(MyConstants.EMAIL).toString(),
                        result.get(MyConstants.PASSWORD).toString(),
                        result.get(MyConstants.GENDER).toString(),
                        result.get(MyConstants.ADDRESS).toString(),
                        result.get(MyConstants.Ordered) as List<String>,
                        result.get(MyConstants.CART) as List<String>
                    )
                    viewModelScope.launch {
                        profileDao.deleteAllData()
                        profileDao.insertData(res)
                    }
                    profileDataRes.set(res)
                    loadProfileImage(res.image.toString())
                    isProgressbarVisible.set(false)
                }
            }
    }

    private fun loadProfileImage(imageName: String) {
        try {
            var firebaseStorage =
                FirebaseStorage.getInstance().getReference(MyConstants.IMAGES + imageName)
            firebaseStorage.downloadUrl.addOnSuccessListener { uri ->
                imageUrl.value = uri.toString()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


    fun updateProfileData(profileDataRes: ProfileDataRes) {
        if (!isConnectionAvailable(context)) {
            showNoNetworkMsg(context)
            return
        }
        isProgressbarVisible.set(true)
        val map: MutableMap<String, Any> = HashMap()
        map[MyConstants.NAME] = profileDataRes.userName.toString()
        map[MyConstants.EMAIL] = profileDataRes.email
        map[MyConstants.PASSWORD] = profileDataRes.password.toString()
        map[MyConstants.PROFILE] = profileDataRes.image.toString()
        map[MyConstants.ADDRESS] = profileDataRes.address.toString()
        map[MyConstants.CONTACT] = profileDataRes.phoneNumber.toString()
        map[MyConstants.Ordered] = profileDataRes.ordered
        map[MyConstants.CART] = profileDataRes.cart
        firebaseFirestore.collection(MyConstants.USERS)
            .document(sharedPrefHelper.getString(MyConstants.EMAIL).toString()).set(map)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    isProgressbarVisible.set(false)
                    isUpdatedProfile.value = true
                    Toast.makeText(
                        context,
                        context.resources.getString(R.string.txt_success),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

    }

    fun uploadImage(imageUri: Uri, imageName: String) {
        var firebaseStorage =
            FirebaseStorage.getInstance().getReference(MyConstants.IMAGES + imageName)
        firebaseStorage.putFile(imageUri).addOnSuccessListener {

        }
    }
}



