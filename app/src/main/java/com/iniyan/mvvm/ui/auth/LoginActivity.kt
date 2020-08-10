package com.iniyan.mvvm_example.ui.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import com.iniyan.mvvm_example.R
import com.iniyan.mvvm_example.databinding.ActivityLoginBinding
import com.iniyan.mvvm_example.util.toast

class LoginActivity : AppCompatActivity() , AuthListener{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // setContentView(R.layout.activity_login)

        /**
         * Binding instance
         */
        val binding :ActivityLoginBinding = DataBindingUtil.setContentView(this,R.layout.activity_login)
        // ActivityLoginBinding -activity_login

        //view model - viewmodelproviders and viewmodelprovider
        val viewModel=ViewModelProviders.of(this).get(AuthViewModel::class.java)
        binding.viewmodel=viewModel

        viewModel.authListener= this // auth listener connect to viewmodel

    }

    override fun onStarted() {
        toast("Login Started")
    }

    override fun onSuccess() {

        toast("Login onSuccess")
    }

    override fun onFailure(message: String) {

        toast("Login OnFailure")
    }
}