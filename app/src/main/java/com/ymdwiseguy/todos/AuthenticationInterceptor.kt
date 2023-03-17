package com.ymdwiseguy.todos

import com.ymdwiseguy.todos.repo.CredentialsRepository
import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import java.net.HttpURLConnection
import java.util.Base64

class AuthenticationInterceptor(
    private val credentialsRepository: CredentialsRepository
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response =
        credentialsRepository.getCredentials()?.let {
            val passPhrase = Base64.getEncoder().encodeToString("${it.username}:${it.password}".toByteArray())

            chain.proceed(
                chain.request().newBuilder().addHeader("Authorization", "Basic $passPhrase").build()
            )

        } ?: fail(chain, "No Access found")

    private fun fail(chain: Interceptor.Chain, reason: String) = Response.Builder()
        .code(HttpURLConnection.HTTP_UNAUTHORIZED)
        .message(reason)
        .request(chain.request().newBuilder().cacheControl(CacheControl.FORCE_NETWORK).build())
        .protocol(Protocol.HTTP_2)
        .body(reason.toResponseBody(MEDIA_TYPE_JSON.toMediaTypeOrNull()))
        .addHeader("content-type", MEDIA_TYPE_JSON)
        .build()

    private companion object {
        const val MEDIA_TYPE_JSON = "application/json"
    }
}