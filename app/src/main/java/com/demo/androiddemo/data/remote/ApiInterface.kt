package com.demo.androiddemo.data.remote

import com.demo.androiddemo.data.local.model.ResUser
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiInterface {

    @GET("api")
    fun loginAsync(@Query("results") modelReq: Int): Deferred<Response<ResUser>>

}
