package com.supraweb.ceibauser.data.network.response

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.supraweb.ceibauser.data.db.entities.usersresponse.Address
import com.supraweb.ceibauser.data.db.entities.usersresponse.Company

import kotlinx.android.parcel.Parcelize
import java.io.Serializable

@Entity
@Parcelize
data class UserDetails(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val name: String,
    val username: String,
    val email: String,
    //val address: Address,
    val phone: String,
    val website: String
   // val company: Company
) : Serializable,Parcelable{

}


/*        TRABAJAR DESPUES  CON CONVERTER OBJECTO TO ROOM

   @Entity
@Parcelize
data class UserDetails(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val name: String,
    val username: String,
    val email: String,
    val address: Address,
    val phone: String,
    val website: String,
    val company: Company
) : Serializable,Parcelable{

}



    */


/*
@Entity
@Parcelize
data class Items(
    @PrimaryKey(autoGenerate = false)
    var cantidadDeProductos: Int,
    var cantidadLimite: Int,
    var fechadeingreso: String,
    var idCodigoDeBarra: String,
    var imagenes: String,
    var iva: Double,
    var marcasDeProductos: String,
    var nombreDelProducto: String,
    var precioUnitario: Double,
    var precioUnitarioMasIva: Double,
    var proveedoresNombres: String,
    var remarcacion: Double,
    var remarcacionResultado: Double,
    var remarcacionResultadoFinal: Double
) : Serializable,Parcelable {

}

 */