package com.ymdwiseguy.todos.viewmodel

import androidx.lifecycle.ViewModel
import com.ymdwiseguy.todos.repo.CredentialsRepository

class StartViewModel(
    credentialsRepository: CredentialsRepository
) : ViewModel() {

    var credentialsAvailable: Boolean = credentialsRepository.getCredentials() != null
}