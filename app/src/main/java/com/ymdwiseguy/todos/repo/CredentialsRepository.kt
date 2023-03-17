package com.ymdwiseguy.todos.repo

class CredentialsRepository(
    private val credentialsSharedPrefs: CredentialsSharedPrefs
) {

    fun getCredentials(): Credentials? = credentialsSharedPrefs.read()
}