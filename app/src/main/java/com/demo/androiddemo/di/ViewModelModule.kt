package com.demo.androiddemo.di

import com.demo.androiddemo.ui.viewmodel.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val viewModelModule = module {

    viewModel {
        MainViewModel(get(), get())
    }
}
