package com.supraweb.ceibauser.data.db.entities.usersresponse

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
data class Geo(
    val lat: String,
    val lng: String
): Parcelable {

}