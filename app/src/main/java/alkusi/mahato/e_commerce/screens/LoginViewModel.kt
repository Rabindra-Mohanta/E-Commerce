package alkusi.mahato.e_commerce.screens

import alkusi.mahato.e_commerce.R
import android.content.Context
import android.view.View
import android.widget.Toast
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    @ApplicationContext private val context: Context
) : BaseViewModel() {
    var firebaseAuth: FirebaseAuth
    var firebaseFirestore: FirebaseFirestore

    init {
        firebaseAuth = FirebaseAuth.getInstance()
        firebaseFirestore = FirebaseFirestore.getInstance()
    }


    var isNavigateToHome = MutableLiveData<Boolean>(false)
    var isNavigateToNewAccount = MutableLiveData<Boolean>(false)
    var edtEmail = MutableLiveData<String>("")
    var edtPassword = MutableLiveData<String>("")
    var isEnableLoginBtn = ObservableBoolean(true)

    fun onLoginBtnClick(view: View) {
        if (!isConnectionAvailable(context)) {
            showNoNetworkMsg(context)
            return
        }
        isEnableLoginBtn.set(false)
        loginUser()

    }

    fun onSignupBtnClick(view: View) {
        isNavigateToNewAccount.value = true
        loginUser()
    }

    fun onForgotPasswordClick(view: View) {}
    private fun loginUser() {
        if (edtEmail.value == null || edtEmail.value!!.isEmpty() || edtPassword.value == null || edtPassword.value!!.isEmpty()) {
            isEnableLoginBtn.set(true)
            Toast.makeText(
                context,
                context.resources.getString(R.string.msg_plz_enter_valid_login),
                Toast.LENGTH_SHORT
            ).show()

        } else {
            firebaseAuth.signInWithEmailAndPassword(
                edtEmail.value.toString(),
                edtPassword.value.toString()
            ).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    isNavigateToHome.value = true
                    isEnableLoginBtn.set(true)
                } else {
                    isEnableLoginBtn.set(true)
                    Toast.makeText(
                        context,
                        context.resources.getString(R.string.msg_plz_enter_valid_login),
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }

    }

}
