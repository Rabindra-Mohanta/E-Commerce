package alkusi.mahato.e_commerce.datahelper

import alkusi.mahato.e_commerce.screens.Constants.MyConstants
import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class SharedPrefHelper @Inject constructor(@ApplicationContext private val context: Context) {
    private val sPref =
        context.getSharedPreferences(MyConstants.SHARE_PREF_NAME, Context.MODE_PRIVATE)
    private val sEdit = sPref.edit()

    //this is code for set the sharedPreferences
    fun setString(variableName: String, value: String) {
        sEdit.putString(variableName, value)
        sEdit.apply()
    }

    fun setBoolean(variableName: String, value: Boolean) {
        sEdit.putBoolean(variableName, value)
        sEdit.apply()
    }

    fun getBoolean(variableName: String): Boolean {
        return sPref.getBoolean(variableName, false)
    }

    // this code is for get sharedPreferences
    fun getString(variableName: String): String? {
        val data = sPref.getString(variableName, "")
        return data
    }
}
