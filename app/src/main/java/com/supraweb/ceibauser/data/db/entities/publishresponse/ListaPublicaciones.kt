package com.supraweb.ceibauser.data.db.entities.publishresponse

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class ListaPublicaciones (
    val userPublishItem:      List<    UserPublishItem >
): Parcelable {

}