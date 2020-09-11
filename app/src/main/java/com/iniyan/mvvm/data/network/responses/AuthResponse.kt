package com.iniyan.mvvm.data.network.responses

import androidx.annotation.Keep
import com.iniyan.mvvm.data.db.entities.User

@Keep
data class AuthResponse(
    val isSuccessful : Boolean?,
    val message: String?,
    val user: User?
)