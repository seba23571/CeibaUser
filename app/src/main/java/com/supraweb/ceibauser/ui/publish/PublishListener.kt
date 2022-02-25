package com.supraweb.ceibauser.ui.publish

import androidx.lifecycle.LiveData
import com.supraweb.ceibauser.data.db.entities.publishresponse.UserPublishItem
import com.supraweb.ceibauser.data.network.response.UserDetails

interface PublishListener {



    fun onFailure(message: String)

    fun onStated()

    fun detailPublicacion(publicacionId: LiveData<List<UserPublishItem>>)

    fun livedataObserver(publicacionId: LiveData<List<UserPublishItem>>)

}