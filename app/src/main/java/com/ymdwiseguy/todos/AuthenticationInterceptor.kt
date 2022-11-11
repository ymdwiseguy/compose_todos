package com.ymdwiseguy.todos

import okhttp3.Interceptor
import okhttp3.Response
import java.util.Base64

class AuthenticationInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {

        // TODO: fetch credentials from shared prefs
        val user = "get from shared prefs!!"
        val pass = "get from shared prefs!!"
        val passPhrase = Base64.getEncoder().encodeToString("$user:$pass".toByteArray())

        return chain.proceed(
            chain.request().newBuilder().addHeader("Authorization", "Basic $passPhrase").build()
        )
    }

}