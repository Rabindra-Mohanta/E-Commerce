package alkusi.mahato.e_commerce.screens.ordered.Dao

import alkusi.mahato.e_commerce.screens.NormalData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface OrderedDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertData(data:List<NormalData>)
    @Query("SELECT * FROM NormalData")
    suspend fun getAllData():List<NormalData>
    @Query("DELETE FROM NormalData")
    suspend fun deleteAllData()
}