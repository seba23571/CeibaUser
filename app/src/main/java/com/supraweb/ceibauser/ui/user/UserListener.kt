package com.supraweb.ceibauser.ui.user

import androidx.lifecycle.LiveData
import com.supraweb.ceibauser.data.db.entities.publishresponse.UserPublishItem
 import com.supraweb.ceibauser.data.network.response.UserDetails

interface UserListener {

    fun detalleDeUsuarios(detallesUser: LiveData<List<UserDetails>>)
    fun onFailure(message: String)

    fun onStated()




    fun idPublishFromRecy(userDetails: UserDetails)



}