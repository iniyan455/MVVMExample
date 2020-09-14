package com.iniyan.mvvm.ui.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.iniyan.mvvm.R
import com.iniyan.mvvm.databinding.ActivitySignupBinding
import com.iniyan.mvvm.util.ApiException
import com.iniyan.mvvm.util.NoInternetException
import com.iniyan.mvvm.util.snackbar
import kotlinx.coroutines.launch
import com.iniyan.mvvm.ui.home.HomeActivity
import com.iniyan.mvvm.util.toast
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class SignupActivity : AppCompatActivity(), KodeinAware {

    override val kodein by kodein()
    private val factory: AuthViewModelFactory by instance()

    private lateinit var binding: ActivitySignupBinding
    private lateinit var viewModel: AuthViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = DataBindingUtil.setContentView(this, R.layout.activity_signup)
        viewModel = ViewModelProvider(this, factory).get(AuthViewModel::class.java)

        viewModel.getLoggedInUser().observe(this, { user ->
            if (user != null) {
                Intent(this, HomeActivity::class.java).also {
                    it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(it)
                }
            }
        })

        binding.buttonSignUp.setOnClickListener {
            userSignup()
        }
    }

    private fun userSignup() {
        val name = binding.editTextName.text.toString().trim()
        val email = binding.editTextEmail.text.toString().trim()
        val password = binding.editTextPassword.text.toString().trim()
        val password1 = binding.editTextPassword.text.toString().trim()

        if(name.isEmpty()){
            toast("Name is required")
            return
        }
        if(email.isEmpty()){
            toast("Email is required")
            return
        }
        if(password.isEmpty()){
            toast("Please enter a password")
            return
        }
        if(password != password1){
            toast("Please enter a password")
            return
        }

        lifecycleScope.launch {
            try {
                val authResponse = viewModel.userSignup(name, email, password)
                if (authResponse.user != null) {
                    viewModel.saveLoggedInUser(authResponse.user)
                } else {
                    binding.root.snackbar(authResponse.message!!)
                }
            } catch (e: ApiException) {
                e.printStackTrace()
            } catch (e: NoInternetException) {
                e.printStackTrace()
            }
        }
    }

}
