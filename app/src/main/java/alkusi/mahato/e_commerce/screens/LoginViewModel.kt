package alkusi.mahato.e_commerce.screens

import alkusi.mahato.e_commerce.R
import alkusi.mahato.e_commerce.screens.Constants.MyConstants
import android.content.Context
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.MutableLiveData
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    @ApplicationContext private val context: Context
) : BaseViewModel() {
    var firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
    private var firebaseFirestore: FirebaseFirestore = FirebaseFirestore.getInstance()
    var isNavigateToHome = MutableLiveData<Boolean>(false)
    var isNavigateToNewAccount = MutableLiveData<Boolean>(false)
    var edtEmail = MutableLiveData<String>("")
    var edtPassword = MutableLiveData<String>("")
    var isEnableLoginBtn = ObservableBoolean(true)
    var isProgressBarVisible = ObservableBoolean(false)

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
    }

    fun onForgotPasswordClick(view: View) {
        forgotPassword()
    }

    private fun loginUser() {
        if (edtEmail.value == null || edtEmail.value!!.isEmpty() || edtPassword.value == null || edtPassword.value!!.isEmpty()) {
            isEnableLoginBtn.set(true)
            Toast.makeText(
                context,
                context.resources.getString(R.string.msg_plz_enter_valid_login),
                Toast.LENGTH_SHORT
            ).show()

        } else {
            //progress bar
            isProgressBarVisible.set(true)
            firebaseAuth.signInWithEmailAndPassword(
                edtEmail.value.toString(),
                edtPassword.value.toString()
            ).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    isNavigateToHome.value = true
                    isEnableLoginBtn.set(true)
                    //progress bar
                    isProgressBarVisible.set(false)
                } else {
                    isEnableLoginBtn.set(true)
                    Toast.makeText(
                        context,
                        context.resources.getString(R.string.msg_plz_enter_valid_login),
                        Toast.LENGTH_SHORT
                    ).show()
                    //progress bar
                    isProgressBarVisible.set(false)
                }
            }
        }

    }

    private fun forgotPassword() {
        if (!isConnectionAvailable(context)) {
            showNoNetworkMsg(context)
            return
        } else {
            val email = edtEmail.value
            if (email != null && email!!.contains(MyConstants.CHECK_EMAIL)) {
                firebaseAuth.sendPasswordResetEmail(email).addOnCompleteListener {
                    Toast.makeText(
                        context,
                        context.resources.getString(R.string.msg_plz_check_your_email),
                        Toast.LENGTH_SHORT
                    ).show()

                }
            } else {
                Toast.makeText(
                    context,
                    context.resources.getString(R.string.txt_enter_email),
                    Toast.LENGTH_SHORT
                ).show()
            }

        }
    }


}
