package alkusi.mahato.e_commerce.screens

import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor():ViewModel() {
    var isNavigateToHome = MutableLiveData<Boolean>(false)
    var isNavigateToNewAccount = MutableLiveData<Boolean>(false)

    fun onLoginBtnClick(view: View)
    {
        Log.e("rabindra","loginBtnClick->"+true)
        isNavigateToHome.value = true
    }
    fun onSignupBtnClick(view:View)
    {
        isNavigateToNewAccount.value = true
    }
    fun onForgotPasswordClick(view: View)
    {}

}