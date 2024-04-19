package com.example.codaquest.ui.components

import androidx.lifecycle.ViewModel
import com.example.codaquest.classes.User

class SharedViewModel: ViewModel() {
    var user: User? = null
        private set

    fun changeUser(newUser: User?) {
        user = newUser
    }

}