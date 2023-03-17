package com.ymdwiseguy.todos.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.ymdwiseguy.todos.repo.Credentials
import com.ymdwiseguy.todos.repo.CredentialsRepository
import java.util.*

class LoginViewModel(
    private val credentialsRepository: CredentialsRepository

) : ViewModel() {

    sealed class LoginViewEvents(
        val eventId: UUID = UUID.randomUUID()
    ) {
        class NavigateToTodos : LoginViewEvents()
        class Error : LoginViewEvents()
    }

    var viewEvents: LoginViewEvents? by mutableStateOf(null)
        private set

    var username by mutableStateOf<String?>(null)
        private set

    var password by mutableStateOf<String?>(null)
        private set

    init {
        credentialsRepository.getCredentials()?.let {
            username = it.username
            password = it.password
        }
    }

    fun updateUserName(username: String) {
        this.username = username
    }

    fun updatePassword(password: String) {
        this.password = password
    }

    fun login() {
        val currentUsername = username
        val currentPassword = password
        viewEvents = if (currentUsername != null && currentPassword != null) {
            credentialsRepository.setCredentials(Credentials(currentUsername, currentPassword))
            LoginViewEvents.NavigateToTodos()
        } else {
            LoginViewEvents.Error()
        }

    }
}