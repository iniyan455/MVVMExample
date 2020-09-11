package com.iniyan.mvvm.ui.auth

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.iniyan.mvvm.R
import com.iniyan.mvvm.data.db.entities.User
import com.iniyan.mvvm.databinding.ActivityLoginBinding
import com.iniyan.mvvm.ui.home.HomeActivity
import com.iniyan.mvvm.util.hide
import com.iniyan.mvvm.util.show
import com.iniyan.mvvm.util.snackbar
import com.iniyan.mvvm.util.toast
import kotlinx.android.synthetic.main.activity_login.*
import org.kodein.di.android.kodein
import org.kodein.di.KodeinAware
import org.kodein.di.generic.instance

class LoginActivity : AppCompatActivity() , AuthListener ,KodeinAware{


    override val kodein by kodein()
    private val factory : AuthViewModelFactory by instance()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // setContentView(R.layout.activity_login)


//        /**
//         * How to do constructor injection
//         * so we required to initiate all and pass to userrepo
//         * but we are passing to viewmodel constructor
//         * currently using viewbinding not passing any constructor
//         * so creating authviewmodelFactory to pass required new instance factory
//         * using viewmodel factory via passing the data
//         * bad practise
//         */
//        val networkConnectionInterceptor= NetworkConnectionInterceptor(this)
//        val api=MyApi(networkConnectionInterceptor=networkConnectionInterceptor)
//        val db=AppDatabase(this)
//        val respository = UserRepository(api,db)
//        val factory=AuthViewModelFactory(repository = respository)

        /**
         * Binding instance
         */
        val binding :ActivityLoginBinding = DataBindingUtil.setContentView(this,R.layout.activity_login)
        // ActivityLoginBinding -activity_login

        //view model - viewmodelproviders and viewmodelprovider
        val viewModel=ViewModelProviders.of(this,factory).get(AuthViewModel::class.java)

        binding.viewmodel=viewModel

        viewModel.authListener= this // auth listener connect to viewmodel


        viewModel.getLoggedInUser().observe(this, Observer {
                user->
            if(user!=null){
                Intent(this,HomeActivity::class.java).also { it.flags =Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(it)}
            }
        })
    }

    override fun onStarted() {
        // progress_bar.visibility= View.VISIBLE instead of this
        progress_bar.show()
        toast("Login Started")
    }

    override fun onSuccess(user: User) {
        progress_bar.hide()
//        root_layout.snackbar("Login onSuccess ${user.name} is Logged In")


    }

    override fun onFailure(message: String) {
        progress_bar.hide()
        root_layout.snackbar("Login OnFailure")

    }

}