package com.supraweb.ceibauser.data.network

import com.supraweb.ceibauser.data.db.entities.publishresponse.UserPublishItem
import com.supraweb.ceibauser.data.network.response.UserDetails
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface MyApi {





    @GET("/users")
    suspend fun getUsers() : Response<List<UserDetails>>




    @GET("/posts")
    suspend fun getPublish( @Query("userId")   userId:Int           ) :Response<List<UserPublishItem>>
    //          //https://jsonplaceholder.typicode.com/posts?userId=1




//    suspend fun adminItems(@Header("Authorization") autToken: String): Response<List<Items>>
    companion object {

        operator fun invoke(
            networkConnectionInterceptor   : NetworkConnectionInterceptor
        ) :MyApi{

            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor(networkConnectionInterceptor)
                .build()
            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("https://jsonplaceholder.typicode.com")

                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MyApi::class.java)
        }
    }

}