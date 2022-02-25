package com.supraweb.ceibauser.ui.user

import android.widget.RelativeLayout
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.supraweb.ceibauser.data.network.response.UserDetails
import com.supraweb.ceibauser.data.repositories.UserRepository
import com.supraweb.ceibauser.util.ApiException
import com.supraweb.ceibauser.util.Coroutines
import com.supraweb.ceibauser.util.NoInternetException
import com.supraweb.ceibauser.util.lazyDeferred
import kotlinx.coroutines.runBlocking

class UserViewModel(
    private val repository: UserRepository

) : ViewModel() {
    //    var authListener :AuthListener ?=null
    var Listener: UserListener? = null

    fun getListUSER() = repository.getUserRoom()


    val listaUserNueva  by lazyDeferred {
        repository.getListUserNew()

    }

    fun checkUserTimeLogin() {
        val usertime = repository.comprobarTiempo()
        if (!usertime) return
        if (usertime) {
            runBlocking {
                repository.usersDelete()
            }
        }
        }

    fun selectSqlUser()           : LiveData<List<UserDetails>> {
     val   usersSelect: LiveData<List<UserDetails>>
        runBlocking {

            usersSelect=   repository.usersSelect()
          //  met(repository.usersSelect()   )   private fun met(usersSelect: LiveData<List<UserDetails>>) {

                      }

        return usersSelect


    }






    fun onLoadUsers(view: RelativeLayout) {

        Coroutines.main {
            try {
                Listener?.onStated()
            val usersRestApi = repository.userRestApi()
            if (usersRestApi!=null){
             //   repository.saveUserDetails(usersRestApi as java.util.ArrayList<UserDetails>    ) // c as List<UserDetails> a     as java.util.ArrayList<UserDetails>
                Listener?.detalleDeUsuarios(     usersRestApi           )

                return@main
            }
                Listener?.onFailure(" error REST ")
            } catch (e : ApiException){
                Listener?.onFailure(" error codigo "+e.message)
            }
            catch (e: NoInternetException){
                Listener?.onFailure( " error code  : " + e.message     )
            }
        }
    }









}