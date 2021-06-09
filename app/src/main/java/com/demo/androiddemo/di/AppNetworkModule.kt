package com.demo.androiddemo.di

import android.util.Log
import com.demo.androiddemo.data.remote.ApiInterface
import com.demo.androiddemo.utils.ApiConstant
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


val networkModule = module {

    single {
        Retrofit.Builder()
            .baseUrl(ApiConstant.BASE_URL)
            .client(get())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    // Dependency: ApiService
    single {
        val retrofit: Retrofit = get()
        retrofit.create(ApiInterface::class.java)
    }

    // Dependency: HttpLoggingInterceptor
    single<Interceptor>(name = "INTERCEPTOR_LOGGING") {
        HttpLoggingInterceptor { message -> Log.d("OkHttp", message) }.apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }


    // Dependency: OkHttpClient
    single {
        OkHttpClient.Builder()
            .connectTimeout(3, TimeUnit.MINUTES)
            .readTimeout(3, TimeUnit.MINUTES)
            .writeTimeout(3, TimeUnit.MINUTES)
            .addInterceptor(get<Interceptor>("INTERCEPTOR_LOGGING"))
            .build()
    }
}
