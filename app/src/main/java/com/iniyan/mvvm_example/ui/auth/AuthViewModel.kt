package com.iniyan.mvvm_example.ui.auth

import android.view.View
import androidx.lifecycle.ViewModel

class AuthViewModel :ViewModel() {

    var email :String ?=null
    var password :String?=null

    var authListener:AuthListener?=null

    fun onLoginButtonClick(view : View){
        authListener?.onStarted()
        if(email.isNullOrEmpty() || password.isNullOrEmpty()){
            authListener?.onFailure("InValid email or password")
            return
        }

        //success
        authListener?.onSuccess()

    }

}