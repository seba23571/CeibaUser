package com.supraweb.ceibauser.data.db


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.supraweb.ceibauser.data.db.entities.UserDetailDao
import com.supraweb.ceibauser.data.network.response.UserDetails


@Database(
    entities = [  UserDetails::class  ],
    version = 3
)

abstract class AppDataBase : RoomDatabase() {
    abstract fun getUserDao(): UserDetailDao
//    abstract fun getItemsDao() :ItemsDao
//    abstract fun guardarProductosDao() :ProductosDao
    companion object {

        @Volatile
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
                "SebasDatabase.db"
            ).build()


    }
}