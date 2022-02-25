package com.supraweb.ceibauser.data.db.entities
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.supraweb.ceibauser.data.network.response.UserDetails


@Database(
    entities = [UserDetails::class],
    version = 1
)
abstract class AppDataBase : RoomDatabase(){

    abstract fun getListUserDao() : UserDetailDao


    companion object {


        private var instance: AppDataBase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also {
                instance = it
            }


        }
        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                AppDataBase::class.java,
                "CeibaDatabase.db"
            ).build()
    }
}