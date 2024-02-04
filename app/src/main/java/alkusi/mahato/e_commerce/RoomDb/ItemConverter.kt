package alkusi.mahato.e_commerce.RoomDb

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ItemConverter {
    @TypeConverter
    fun fromString(strList:List<String>):String
    {
        return Gson().toJson(strList)
    }
    @TypeConverter
    fun strToList(str:String):List<String>
    {
        val type = object : TypeToken<List<String>>() {}.type
        return Gson().fromJson(str,type)
    }
}