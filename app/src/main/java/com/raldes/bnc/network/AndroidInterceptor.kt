package com.raldes.bnc.network

import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException

class AndroidInterceptor: Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()
            .header("Content-Type", "application/json")

        val request = requestBuilder.build()
        return chain.proceed(request)
    }
}