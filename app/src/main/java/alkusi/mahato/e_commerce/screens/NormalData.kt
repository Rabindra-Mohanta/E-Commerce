package alkusi.mahato.e_commerce.screens

import alkusi.mahato.e_commerce.Constants.TableName
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = TableName.NormalData)
data class NormalData ( @PrimaryKey(autoGenerate = false) val title:String, val image:String, var originalPrice:String, val offerPrice:String )
