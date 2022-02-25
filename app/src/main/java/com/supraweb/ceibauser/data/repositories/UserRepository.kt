package com.supraweb.ceibauser.data.repositories

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.supraweb.ceibauser.data.db.AppDataBase
import com.supraweb.ceibauser.data.db.entities.publishresponse.UserPublishItem
import com.supraweb.ceibauser.data.network.MyApi
import com.supraweb.ceibauser.data.network.SafeApiRequest
import com.supraweb.ceibauser.data.network.response.UserDetails
import com.supraweb.ceibauser.util.Coroutines
import com.supraweb.data.preferences.PreferencesProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.joda.time.Hours
import org.joda.time.Instant

class UserRepository(

    private val api: MyApi,
    private val db: AppDataBase,
    private val pref: PreferencesProvider

) : SafeApiRequest() {
    private val allUserLiveData = MutableLiveData<List<UserDetails>>()

    private val allUserLiveDataGuardar = MutableLiveData<List<UserDetails>>()


    private val LivePublish = MutableLiveData<List<UserPublishItem>>()


    init {

        allUserLiveDataGuardar.observeForever { listObservaBle ->

            saveUserDetailList(listObservaBle)

        }

    }

    private fun saveUserDetailList(listObservaBle: List<UserDetails>) {
        val fechaNueva = System.currentTimeMillis()
        val testNow: Instant = Instant.ofEpochMilli(fechaNueva)

        Coroutines.io {
            pref.saveLastSavedAt(testNow.toDateTime().toString())
            db.getUserDao().insertUserDetailList(listObservaBle)
        }
    }

    suspend fun getListUserNew(): LiveData<List<UserDetails>> {

        return withContext(Dispatchers.IO) {

            fetchUser()
            db.getUserDao().getUserDetailList()

        }
    }

    private suspend fun fetchUser() {

       if(comprobarTiempo()){
           val response = apiRequest {
               api.getUsers()
           }
           allUserLiveDataGuardar.postValue(response)
       }
//        if (lastSaveAt == null  ||            isFetchNeeded(booleanisFetchNeeded)      ) {
//            val response = apiRequest {
//                api.getUsers()
//            }
//            allUserLiveDataGuardar.postValue(response)
//        }



    }

    fun comprobarTiempo(): Boolean {
        val fechaNueva = System.currentTimeMillis()
        val testNow: Instant = Instant.ofEpochMilli(fechaNueva)

        val lastSavedAt = pref.getLastSavedAt()// <string name="key_saved_at">2021-08-26T09:07:40.734-03:00</string>

        if (lastSavedAt == null) {


            pref.saveLastSavedAt(testNow.toDateTime().toString()         )

            return false
        }
        val strparse = Instant.parse(lastSavedAt)
        //   Hours.hoursBetween(testNow.toDateTime(), strparse.toDateTime() )
        if ( Hours.hoursBetween( strparse    , testNow ).getHours()  > 6     )       {
            pref.saveLastSavedAt(        testNow.toDateTime().toString()              )
            return true
        }

        return false
    }

    suspend fun usersDelete() {

        db.getUserDao().deleteUserAll()
    }


      fun usersSelect()    : LiveData<List<UserDetails>> {

       return db.getUserDao().getUserDetailList()
    }

    private fun isFetchNeeded(booleanisFetchNeeded: Boolean): Boolean {
        val test : Boolean
        // comprobar si paso las seis horas
        if(booleanisFetchNeeded) {
            test =false
        //paso seis horas
        }else{
            test =true
        }

        return test

    }

    suspend fun userRestApi(): LiveData<List<UserDetails>> {  //aca es recycler view  Pero no guarda nada en base de datos
        val response = apiRequest {

            api.getUsers()

        }
        // allUserLiveDataGuardar.postValue(response)
        // return  MyApi().getUsers()
        allUserLiveData.postValue(response)
        allUserLiveDataGuardar.postValue(response)
        return allUserLiveData
    }


    suspend fun userPublis(userId: Int): LiveData<List<UserPublishItem>> {
        val response = apiRequest {

            api.getPublish(userId)

        }

        LivePublish.postValue(response)
        return LivePublish

    }


    suspend fun saveUserDetails(userDetail: List<UserDetails>) =
        db.getUserDao().insertUserDetailList(userDetail)


    fun getUserRoom() = db.getUserDao().getUserDetailList()


}