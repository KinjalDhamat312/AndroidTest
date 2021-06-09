package com.demo.androiddemo.di

import androidx.room.Room
import com.demo.androiddemo.data.local.AppDatabase
import com.demo.androiddemo.data.local.UserLocalDataSource
import com.demo.androiddemo.data.remote.RemoteDataSource
import com.demo.androiddemo.utils.AppConstant.DATABASE_NAME
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module.module

val dataBaseModule = module {

    single {
        Room.databaseBuilder(
            androidContext(),
            AppDatabase::class.java, DATABASE_NAME
        ).build()
    }

    single {
        val appDatabase: AppDatabase = get()
        appDatabase.userDao()
    }

    single {
        UserLocalDataSource(get())
    }

    single {
        RemoteDataSource()
    }
}
