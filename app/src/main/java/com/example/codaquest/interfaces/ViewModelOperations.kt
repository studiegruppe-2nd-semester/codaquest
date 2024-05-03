package com.example.codaquest.interfaces

import com.example.codaquest.Models.User

interface UserOperations {
    fun updateKey(newKey: String)
    fun changeUser(newUser: User?)
}

interface ErrorOperations {
    fun showError(error: String)
}

interface NavigationOperations {
    fun navigate(route: String)
    // Add other navigation methods as needed
}