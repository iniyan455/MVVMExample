package com.iniyan.mvvm.ui.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.iniyan.mvvm.data.repository.UserRepository

@Suppress("UNCHECKED_CAST")
class AuthViewModelFactory(
    private  val repository: UserRepository
) :ViewModelProvider.NewInstanceFactory(){
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return AuthViewModel(repository = repository) as T
    }
}