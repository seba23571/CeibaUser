package com.supraweb.ceibauser.data.db.entities.publishresponse

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class UserPublishItem(
    val body: String,
    val id: Int,
    val title: String,
    val userId: Int
): Parcelable {

}