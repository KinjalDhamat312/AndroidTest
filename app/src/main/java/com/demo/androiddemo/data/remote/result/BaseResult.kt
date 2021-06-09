package com.demo.androiddemo.data.remote.result

import androidx.annotation.Keep
import okhttp3.Headers

@Keep
open class BaseResult {
    var message: String? = null
    var code: String? = null
}
