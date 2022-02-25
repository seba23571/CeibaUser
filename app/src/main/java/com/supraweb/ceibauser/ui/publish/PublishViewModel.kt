package com.supraweb.ceibauser.ui.publish

import android.widget.RelativeLayout
import androidx.lifecycle.ViewModel
import com.supraweb.ceibauser.data.repositories.UserRepository
import com.supraweb.ceibauser.util.ApiException
import com.supraweb.ceibauser.util.Coroutines
import com.supraweb.ceibauser.util.NoInternetException

class PublishViewModel (

    private val repository: UserRepository
        ): ViewModel() {


    var listenerInterfaz: PublishListener? = null




    fun getPublishByID(root: RelativeLayout, id: Int){

        Coroutines.main {
            try {
                listenerInterfaz?.onStated()
                val publicacionIdAPI =    repository.userPublis(id)
                if (publicacionIdAPI!=null){
                    listenerInterfaz?.livedataObserver(publicacionIdAPI)
                    return@main

                }
                listenerInterfaz?.onFailure(" error REST ")
                //  met(publicacionId)      private fun met(     publicacionId: LiveData<List<UserPublishItem>>      ) {

            }catch (e: ApiException){
                listenerInterfaz?.onFailure(" error codigo "+e.message)
            }
            catch (e: NoInternetException){
                listenerInterfaz?.onFailure( " error code  : " + e.message     )
            }

        }

    }
}