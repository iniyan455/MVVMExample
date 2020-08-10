package com.iniyan.mvvm_example.ui.auth

import android.os.Message

interface AuthListener {

    fun onStarted()
    fun onSuccess()
    fun onFailure(message: String)
}