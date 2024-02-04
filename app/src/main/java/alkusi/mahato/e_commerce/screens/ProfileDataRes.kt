package alkusi.mahato.e_commerce.screens

import alkusi.mahato.e_commerce.Constants.TableName
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = TableName.ProfileDataRes)
data class ProfileDataRes( var image:String?, var userName:String?,var phoneNumber:String?,@PrimaryKey(autoGenerate = false) var email:String,var password:String?,var gender:String?,var address:String?,var ordered:List<String>,var cart:List<String>)