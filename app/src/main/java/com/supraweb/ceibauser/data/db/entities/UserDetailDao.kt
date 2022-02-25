package com.supraweb.ceibauser.data.db.entities

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.supraweb.ceibauser.data.network.response.UserDetails
import kotlinx.coroutines.flow.Flow


@Dao
interface UserDetailDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend  fun insertUserDetailList(userDetail: List<UserDetails>) //: Long

    @Query("SELECT * FROM UserDetails")
    fun getUserDetailList(): LiveData<List<UserDetails>>

    @Query("SELECT * FROM  UserDetails  WHERE   UserDetails.name LIKE :testSearch ")
    fun getBusquedaByName(testSearch: String): Flow<List<UserDetails>>

    @Query("DELETE     FROM UserDetails" )
    suspend fun deleteUserAll( )


    /*
@Insert (onConflict = OnConflictStrategy .REPLACE)
fun saveAllItems(items :List<Items>)

@Query("SELECT * FROM Items")
fun getItems() : LiveData<List<Items>>


@Query("SELECT * FROM  Items  WHERE   nombreDelProducto LIKE :testSearch " )
fun getBusqueda(testSearch : String) : Flow<List<Items>>
     */

}