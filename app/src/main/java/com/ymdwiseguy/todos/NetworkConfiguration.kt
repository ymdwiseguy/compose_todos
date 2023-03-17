package com.ymdwiseguy.todos

import com.squareup.moshi.Moshi
import com.ymdwiseguy.todos.repo.CredentialsRepository
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object NetworkConfiguration {
    fun buildRetrofit(
        credentialsRepository: CredentialsRepository,
        moshi: Moshi,
    ): Retrofit = Retrofit.Builder()
        .baseUrl("http://matthias-diekmann.de/scripts/")
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .client(
            OkHttpClient.Builder()
                .addInterceptor(AuthenticationInterceptor(credentialsRepository))
                .build()
        )
        .build()
}