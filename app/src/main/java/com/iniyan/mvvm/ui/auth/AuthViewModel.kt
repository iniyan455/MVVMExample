package com.iniyan.mvvm.ui.auth

import android.content.Intent
import android.view.View
import androidx.lifecycle.ViewModel
import com.iniyan.mvvm.data.db.entities.User
import com.iniyan.mvvm.data.repository.UserRepository
import com.iniyan.mvvm.util.ApiException
import com.iniyan.mvvm.util.NoInternetException
import net.simplifiedcoding.mvvmsampleapp.util.Coroutines

class AuthViewModel(private val repository: UserRepository) :ViewModel() {

    var email :String ?=null
    var password :String?=null
    var name :String? = null
    var authListener:AuthListener?=null

    var passwordConfirm:String? = null

    fun getLoggedInUser() = repository.getUser()

    fun onLoginButtonClick(view : View) {
        authListener?.onStarted()
        if(email.isNullOrEmpty() || password.isNullOrEmpty()){
            authListener?.onFailure("InValid email or password")
            return
        }


        // bad practise creating userrepository  class instance creation depedent on userrepo  use dependency injection to avoid this
//        val loginResponse = UserRepository().userLoginOld(email=email!!,password = password!!)
//        authListener?.onSuccess(loginResponse)
        //success

        Coroutines.main {
            try {
                /**
                 * val authResponse =UserRepository().userLogin(email=email!!,password = password!!)
                 * Instead of this do constructor injection
                 */

                val authResponse =repository.userLogin(email=email!!,password = password!!)

                authResponse.user?.let {
                    authListener?.onSuccess(it)
                    repository.saveUser(it)
                    return@main
                }
                authListener?.onFailure(authResponse.message!!)

            }catch (e:ApiException){
                authListener?.onFailure(e.message!!)
            }
            catch (e:NoInternetException){
                authListener?.onFailure(e.message!!)
            }

        }
        //authListener?.onSuccess()

    }

    fun onLogin(view: View){
        Intent(view.context,LoginActivity::class.java).also { view.context.startActivity(it) }
    }
    fun onSignup(view: View){
        Intent(view.context,SignupActivity::class.java).also { view.context.startActivity(it) }
    }

    fun onSignUpButtonClick(view : View) {
        authListener?.onStarted()

        if(name.isNullOrEmpty()){
            authListener?.onFailure("Name is required")
            return
        }
        if(email.isNullOrEmpty()){
            authListener?.onFailure("Email is required")
            return
        }
        if(password.isNullOrEmpty()){
            authListener?.onFailure("Please enter a password")
            return
        }
        if(password != passwordConfirm){
            authListener?.onFailure("Please enter a password")
            return
        }


        // bad practise creating userrepository  class instance creation depedent on userrepo  use dependency injection to avoid this
//        val loginResponse = UserRepository().userLoginOld(email=email!!,password = password!!)
//        authListener?.onSuccess(loginResponse)
        //success

        Coroutines.main {
            try {
                /**
                 * val authResponse =UserRepository().userLogin(email=email!!,password = password!!)
                 * Instead of this do constructor injection
                 */

                val authResponse =repository.userSignup(name=name
                !!,email = email!!,password = password!!)

                authResponse.user?.let {
                    authListener?.onSuccess(it)
                    repository.saveUser(it)
                    return@main
                }
                authListener?.onFailure(authResponse.message!!)

            }catch (e:ApiException){
                authListener?.onFailure(e.message!!)
            }
            catch (e:NoInternetException){
                authListener?.onFailure(e.message!!)
            }

        }
        //authListener?.onSuccess()

    }


}