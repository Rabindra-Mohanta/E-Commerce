package alkusi.mahato.e_commerce.screens.Profile.Dao

import alkusi.mahato.e_commerce.screens.OrderedDataRes
import alkusi.mahato.e_commerce.screens.ProfileDataRes
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ProfileDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertData(data:ProfileDataRes)
    @Query("SELECT * FROM ProfileDataRes")
    suspend fun getAllData():ProfileDataRes
    @Query("DELETE FROM ProfileDataRes")
    suspend fun deleteAllData()
}