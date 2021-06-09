package com.demo.androiddemo.data.repository


import androidx.lifecycle.MutableLiveData
import com.demo.androiddemo.data.local.UserLocalDataSource
import com.demo.androiddemo.data.remote.ApiInterface
import com.demo.androiddemo.data.remote.RemoteDataSource
import com.demo.androiddemo.data.local.model.ResUser


class UserRepository constructor(
    private var remoteDataSource: RemoteDataSource,
    private var localDataSource: UserLocalDataSource,
    private var apiInterface: ApiInterface,
) {

    //    Get user list from server
    suspend fun getRemoteUsers(isLoading: MutableLiveData<Boolean>) {
        val result = remoteDataSource.apiCall(call = { apiInterface.loginAsync(50).await() })
        if (result is ResUser) {
            localDataSource.saveUsers(result.results)
        }
        isLoading.postValue(false)
    }

    //    Get user list from local database
    fun getLocalUsers() = localDataSource.getUsers()
}
