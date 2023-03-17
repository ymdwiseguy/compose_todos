package com.ymdwiseguy.todos.repo

import android.content.SharedPreferences
import com.squareup.moshi.Moshi

class CredentialsSharedPrefs(
    private val sharedPreferences: SharedPreferences,
    moshi: Moshi,
) {

    private val adapter = moshi.adapter(Credentials::class.java)

    fun write(credentials: Credentials) = sharedPreferences.edit().putString(
        CREDENTIALS_KEY, adapter.toJson(credentials)
    ).apply()

    fun read(): Credentials? = sharedPreferences.getString(CREDENTIALS_KEY, null)?.let {
        adapter.fromJson(it)
    }

    private companion object {
        const val CREDENTIALS_KEY = "CREDENTIALS_KEY"
    }
}

data class Credentials(
    val username: String,
    val password: String,
)