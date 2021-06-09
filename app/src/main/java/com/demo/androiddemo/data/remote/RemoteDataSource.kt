package com.demo.androiddemo.data.remote

import com.demo.androiddemo.data.remote.result.BaseError
import com.demo.androiddemo.data.remote.result.BaseResult
import com.demo.androiddemo.data.remote.result.Resource
import com.demo.androiddemo.utils.ApiConstant
import org.json.JSONObject
import retrofit2.Response
import java.net.ConnectException

open class RemoteDataSource( ) {

    suspend fun <T : BaseResult> apiCall(call: suspend () -> Response<T>): BaseResult? {
        val result: Resource<T> = safeApiResult(call)
        var data: BaseResult? = null

        when (result) {
            is Resource.Success -> data = result.data
            is Resource.Error -> data = result.error
        }
        return data
    }

    private suspend fun <T : BaseResult> safeApiResult(call: suspend () -> Response<T>): Resource<T> {
        try {
            val response = call.invoke()
            if (response.isSuccessful) {
                return if (response.body() != null && response.body() is BaseResult) {

                    val baseResult = response.body() as BaseResult
                    if (response.code() == 200) {
                        Resource.Success(response.body()!!)
                    } else {
                        val baseError = BaseError()
                        Resource.Error(baseError)
                    }
                } else {
                    val baseError = BaseError()
                    baseError.code = ApiConstant.SOMETHING_WRONG_ERROR_STATUS
                    baseError.message = ApiConstant.SOMETHING_WRONG_ERROR
                    Resource.Error(baseError)
                }
            } else {
                if (response.errorBody() != null) {

                    if (response.headers()["Content-Type"]?.contains("application/json")!!) {//check if response is in json format

                        val errorString = response.errorBody()?.string()
                        val baseError = BaseError()
                        if (errorString?.isEmpty() == false) {
                            val jsonResponse = JSONObject(errorString)
                            try {
                                baseError.code = response.code().toString()
                                baseError.message = jsonResponse.get("error").toString()

                            } catch (e: Exception) {
                                baseError.code = ApiConstant.SOMETHING_WRONG_ERROR_STATUS
                                baseError.message = ApiConstant.SOMETHING_WRONG_ERROR
                            }
                        } else {
                            baseError.code = ApiConstant.SOMETHING_WRONG_ERROR_STATUS
                            baseError.message = ApiConstant.SOMETHING_WRONG_ERROR
                        }
                        return Resource.Error(baseError)

                    } else {
                        val baseError = BaseError()
                        baseError.code = ApiConstant.SOMETHING_WRONG_ERROR_STATUS
                        baseError.message = ApiConstant.SOMETHING_WRONG_ERROR
                        return Resource.Error(baseError)
                    }
                } else {
                    val baseError = BaseError()
                    baseError.code = ApiConstant.SOMETHING_WRONG_ERROR_STATUS
                    baseError.message = ApiConstant.SOMETHING_WRONG_ERROR
                    return Resource.Error(baseError)
                }

            }
        } catch (error: Exception) {
            return when (error) {
                is ConnectException -> {
                    val baseError = BaseError()
                    baseError.code = ApiConstant.TIME_OUT_CONNECTION_STATUS
                    baseError.message = ApiConstant.TIME_OUT_CONNECTION
                    Resource.Error(baseError)
                }

                else -> {
                    val baseError = BaseError()
                    baseError.code = ApiConstant.SOMETHING_WRONG_ERROR_STATUS
                    baseError.message = ApiConstant.SOMETHING_WRONG_ERROR
                    Resource.Error(baseError)
                }
            }

        }

    }
}
