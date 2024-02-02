package alkusi.mahato.e_commerce.screens

import alkusi.mahato.e_commerce.R
import alkusi.mahato.e_commerce.datahelper.SharedPrefHelper
import alkusi.mahato.e_commerce.Constants.MyConstants
import android.content.Context
import android.view.View
import android.widget.Toast
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject


@HiltViewModel
class SignupViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val sharedPrefHelper: SharedPrefHelper
) :
    BaseViewModel() {
    var isEnableCreateBtn = ObservableBoolean(true)
    var isProgressBarVisible = ObservableBoolean(false)
    var isNavigateToHome = MutableLiveData<Boolean>(false)
    var edtName = MutableLiveData<String>("")
    var edtEmail = MutableLiveData<String>("")
    var edtPassword = MutableLiveData<String>("")
    var edtConfirmPassword = MutableLiveData<String>("")
    var firebaseFirestore: FirebaseFirestore = FirebaseFirestore.getInstance()
    var firebaseAuth = FirebaseAuth.getInstance()

    fun onCreateClick(view: View) {
        if (!isValid())
            return
        if (!isConnectionAvailable(context)) {
            showNoNetworkMsg(context)
            return
        }
        isEnableCreateBtn.set(false)
        isProgressBarVisible.set(true)
        createAccountInFireStore()

    }

    private fun createAccountInFireStore() {
        val map: MutableMap<String, Any> = HashMap()
        map[MyConstants.NAME] = edtName.value.toString()
        map[MyConstants.EMAIL] = edtEmail.value.toString()
        map[MyConstants.PASSWORD] = edtPassword.value.toString()
        map[MyConstants.PROFILE] = ""
        map[MyConstants.ADDRESS] = ""
        map[MyConstants.CONTACT] = ""
        firebaseFirestore.collection(MyConstants.USERS).document(edtEmail.value.toString()).set(map)
            .addOnSuccessListener(object : OnSuccessListener<Void> {
                override fun onSuccess(p0: Void?) {
                    // now create account in auth
                    createAuthAccount()
                }

            }).addOnFailureListener {
                Toast.makeText(
                    context,
                    context.resources.getString(R.string.something_went_wrong),
                    Toast.LENGTH_SHORT
                ).show()
                isEnableCreateBtn.set(true)
                isProgressBarVisible.set(false)
            }
    }


    private fun isValid(): Boolean {
        if (edtEmail.value == null || edtEmail.value!!.isEmpty() || edtName.value == null || edtName.value!!.isEmpty()) {
            Toast.makeText(
                context,
                context.resources.getString(R.string.msg_enter_valid_details),
                Toast.LENGTH_SHORT
            ).show()

            return false
        }

        if (edtPassword.value == null || edtPassword.value!!.isEmpty() || edtConfirmPassword.value == null || edtConfirmPassword.value!!.isEmpty() || edtPassword.value != edtConfirmPassword.value) {
            Toast.makeText(
                context,
                context.resources.getString(R.string.msg_enter_valid_password),
                Toast.LENGTH_SHORT
            ).show()
            return false
        }
        return true

    }

    private fun createAuthAccount() {
        firebaseAuth.createUserWithEmailAndPassword(
            edtEmail.value.toString(),
            edtPassword.value.toString()
        ).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Toast.makeText(
                    context,
                    context.resources.getString(R.string.txt_success),
                    Toast.LENGTH_SHORT
                ).show()
                //save in shared
                saveLoginDataToSharedPref()
                isEnableCreateBtn.set(true)
                isProgressBarVisible.set(false)
                isNavigateToHome.value = true
            } else {
                Toast.makeText(
                    context,
                    context.resources.getString(R.string.something_went_wrong),
                    Toast.LENGTH_SHORT
                ).show()
                isEnableCreateBtn.set(true)
                isProgressBarVisible.set(false)
            }

        }

    }

    private fun saveLoginDataToSharedPref() {
        sharedPrefHelper.setString(MyConstants.EMAIL, edtEmail.value.toString())
        sharedPrefHelper.setString(MyConstants.PASSWORD, edtPassword.value.toString())
    }

}