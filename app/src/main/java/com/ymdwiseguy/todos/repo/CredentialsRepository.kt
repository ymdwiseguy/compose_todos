package com.ymdwiseguy.todos.repo

class CredentialsRepository(
    private val credentialsSharedPrefs: CredentialsSharedPrefs
) {

    fun getCredentials(): Credentials? = credentialsSharedPrefs.read()

    fun setCredentials(credentials: Credentials) {
        credentialsSharedPrefs.write(credentials = credentials)
    }
}