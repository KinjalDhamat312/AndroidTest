package com.demo.androiddemo.di


import com.demo.androiddemo.data.repository.UserRepository
import org.koin.dsl.module.module

val repositoryModule = module {


    single {
        UserRepository(
            get(),
            get(),
            get(),
        )
    }

}
