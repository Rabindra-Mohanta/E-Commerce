package alkusi.mahato.e_commerce.screens
import alkusi.mahato.e_commerce.R
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.widget.Toast
import androidx.lifecycle.ViewModel
open class BaseViewModel:ViewModel() {
    fun isConnectionAvailable(context: Context):Boolean
    {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork?:return false
        val activeNetwork =connectivityManager.getNetworkCapabilities(network)?:return  false
        return when
        {
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            else -> {false}
        }
    }

    fun showNoNetworkMsg(context: Context)
    {

       Toast.makeText(context,context.resources.getString(R.string.msg_no_internet),Toast.LENGTH_SHORT).show()
    }

}