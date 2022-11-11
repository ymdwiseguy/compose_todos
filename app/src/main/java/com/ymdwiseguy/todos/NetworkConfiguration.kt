package com.ymdwiseguy.todos

import com.squareup.moshi.Moshi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object NetworkConfiguration {
    fun buildRetrofit(moshi: Moshi): Retrofit = Retrofit.Builder()
         .baseUrl("http://matthias-diekmann.de/scripts/")
         .addConverterFactory(MoshiConverterFactory.create(moshi))
         .client(
             OkHttpClient.Builder()
                 .addInterceptor(AuthenticationInterceptor())
                 .build()
         )
         .build()
}