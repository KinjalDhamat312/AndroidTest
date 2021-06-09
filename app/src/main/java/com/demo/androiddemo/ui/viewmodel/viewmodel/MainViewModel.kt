package com.demo.androiddemo.ui.viewmodel.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.demo.androiddemo.data.repository.UserRepository
import com.demo.androiddemo.data.local.model.UserData
import com.demo.androiddemo.utils.isNetworkAvailable
import kotlinx.coroutines.launch

class MainViewModel(application: Application, private val userRepository: UserRepository) :
    AndroidViewModel(application) {

    var userLiveData: LiveData<List<UserData>> = userRepository.getLocalUsers()

    var isLoading: MutableLiveData<Boolean> = MutableLiveData<Boolean>().apply { value = true }

    init {
        if (isNetworkAvailable(getApplication())) {
            getUsersList()
        } else {
            isLoading.value = false
        }
    }

    /**
     * Fetch user list from server
     */
    private fun getUsersList() {
        viewModelScope.launch {
            userRepository.getRemoteUsers(isLoading)
        }
    }

}