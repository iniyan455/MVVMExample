package com.iniyan.mvvm.ui.auth

import com.iniyan.mvvm.data.db.entities.User

interface AuthListener {
    fun onStarted()
    fun onSuccess(user :User)
    fun onFailure(message: String)
}