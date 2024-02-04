package alkusi.mahato.e_commerce.RoomDb

import alkusi.mahato.e_commerce.screens.Cart.Dao.CartDao
import alkusi.mahato.e_commerce.screens.NormalData
import alkusi.mahato.e_commerce.screens.OrderedDataRes
import alkusi.mahato.e_commerce.screens.Profile.Dao.ProfileDao
import alkusi.mahato.e_commerce.screens.ProfileDataRes
import alkusi.mahato.e_commerce.screens.ordered.Dao.OrderedDao
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [OrderedDataRes::class,NormalData::class,ProfileDataRes::class], version = 1)
@TypeConverters(ItemConverter::class)
abstract class MyRoomDb:RoomDatabase() {
    abstract fun getCartDao():CartDao
    abstract fun getOrderedDao():OrderedDao
    abstract fun getProfileDao():ProfileDao

}