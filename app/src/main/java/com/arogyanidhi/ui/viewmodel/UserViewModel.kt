package com.arogyanidhi.ui.viewmodel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserSession @Inject constructor() {
    private val _userEmail = MutableStateFlow("")
    val userEmail: StateFlow<String> = _userEmail.asStateFlow()

    private val _userName = MutableStateFlow("Divya")
    val userName: StateFlow<String> = _userName.asStateFlow()

    fun setUser(email: String, name: String = "Divya") {
        _userEmail.value = email
        _userName.value = name
    }

    fun logout() {
        _userEmail.value = ""
        _userName.value = "Guest"
    }
}

@HiltViewModel
class UserViewModel @Inject constructor(
    private val userSession: UserSession
) : ViewModel() {
    val userEmail = userSession.userEmail
    val userName = userSession.userName

    fun login(email: String) {
        // In a real app, we'd fetch the name from a DB or API
        val name = email.split("@").firstOrNull()?.replaceFirstChar { it.uppercase() } ?: "User"
        userSession.setUser(email, name)
    }

    fun logout() {
        userSession.logout()
    }
    
    fun updateName(newName: String) {
        userSession.setUser(userSession.userEmail.value, newName)
    }
}
