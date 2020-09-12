package com.iniyan.mvvm.ui.home.profile

import androidx.lifecycle.ViewModel;
import com.iniyan.mvvm.data.repository.UserRepository

class ProfileViewModel(
    repository: UserRepository
) : ViewModel() {

    val user = repository.getUser()

}
