package com.demo.androiddemo.data.local

import com.demo.androiddemo.data.local.model.UserData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * Concrete implementation of a data source as a db.
 */
class UserLocalDataSource internal constructor(
    private val userDao: UserDao,
) {

    suspend fun saveUsers(results: List<UserData>?) = withContext(Dispatchers.IO) {
        results?.let {
            userDao.insertUsers(results)
        }
    }

    fun getUsers() = userDao.getUsers()

}
