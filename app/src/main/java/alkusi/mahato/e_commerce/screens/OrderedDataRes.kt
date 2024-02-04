package alkusi.mahato.e_commerce.screens

import alkusi.mahato.e_commerce.Constants.TableName
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = TableName.OrderedDataRes)
class OrderedDataRes(@PrimaryKey(autoGenerate = false) val title:String, val image:String, var originalPrice:String, val offerPrice:String, val itemCount:String)
