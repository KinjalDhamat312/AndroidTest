package com.demo.androiddemo

import android.app.Application
import com.demo.androiddemo.di.dataBaseModule
import com.demo.androiddemo.di.networkModule
import com.demo.androiddemo.di.repositoryModule
import com.demo.androiddemo.di.viewModelModule
import org.koin.android.ext.android.startKoin


class App : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
        iniKoinModules()
    }

    companion object {
        var instance: App? = null
    }

    override fun onTerminate() {
        super.onTerminate()
        if (instance != null) {
            instance = null
        }
    }


    private fun iniKoinModules() {
        startKoin(
            this,
            listOf(repositoryModule, viewModelModule, networkModule, dataBaseModule)
        )
    }

}
