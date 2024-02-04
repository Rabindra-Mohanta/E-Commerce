package alkusi.mahato.e_commerce.screens.Cart.Dao

import alkusi.mahato.e_commerce.screens.NormalData
import alkusi.mahato.e_commerce.screens.OrderedDataRes
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CartDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertData(data:List<NormalData>)
    @Query("SELECT * FROM NormalData")
    suspend fun getAllData():List<NormalData>
    @Query("DELETE FROM NormalData")
    suspend fun deleteAllData()
}