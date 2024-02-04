package alkusi.mahato.e_commerce.di

import alkusi.mahato.e_commerce.Constants.TableName
import alkusi.mahato.e_commerce.RoomDb.MyRoomDb
import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
class RoomModule {
    @Singleton
    @Provides
    fun getRoom(@ApplicationContext context: Context):MyRoomDb
    {
        return Room.databaseBuilder(context,MyRoomDb::class.java,TableName.DBNAME).build()
    }
}