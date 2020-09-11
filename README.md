# MVVMExample

Why Design Patterns 

Makes the code more understandable 
Makes the code maitainable for long run 
Makes the project loosely coupled
Makes the code testable 
Making changes , adding new features are easy 

Architectural principal 

Separation of Concerns 
       - Common problem 
              In Fragment class , Retrofit trying to invoke api -> retrofit -> update to view 
              
              ex :if i update destroyed fragment ui update it leads to crash 
              
              
Drive Ui from Model  - We should derive data from the model and that is responsible for an handling data 
of the application and it is independent from the views because it is independent from the view it become unaffected from the views 


Now our Main Question is ?

Why MVVM ?
We have other design patterns as well and we have already other design patterns as well

1. MVP
2. MVVM
3. MVC

the selection of pattern completed dependent of their needs . There are no perfect patterns exist 
that solves all the problem.
Every Pattern have the own advantages and disadvantanges according to their problems 

Google recommends MVVM is never setup by google 

From Developer community MVVM is popularly recommanded 
Google Says We should use it but also if we follow other architecture patterns tells keep using it. 

Its just random selection MVVM project of this application. 


Architecture :-

Stage 1 :  Activity / Fragment 

activity is dependent on to get the data 

Stage 2 :  View Model 

vuew model dependent on repository 

Stage 3 :  Repository 

repository responsible for model or different data source 

Stage 4 : Model (Ex : Room) or Remote DataSource  ( Retrofit - WebService)



it is very important we need to follow the principles 


  // Retrofit and GSON
    implementation 'com.squareup.retrofit2:retrofit:2.9.0'
    implementation 'com.squareup.retrofit2:converter-gson:2.6.2'

    To write asynchronous code easily 
    //Kotlin Coroutines
    implementation 'org.jetbrains.kotlinx:kotlinx-coroutines-android:1.3.7'
    implementation 'org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.8'

   
    // Kodein Dependency Injection
       implementation 'org.kodein.di:kodein-di-generic-jvm:6.2.1'
       implementation 'org.kodein.di:kodein-di-framework-android-x:6.2.1'
   
       // Android Room
       implementation 'androidx.room:room-runtime:2.2.5'
       implementation 'androidx.room:room-ktx:2.2.5'
       kapt 'androidx.room:room-compiler:2.2.5'


    //ViewModel and LiveData
    implementation 'androidx.lifecycle:lifecycle-extensions:2.2.0'





  /**
         * How to do constructor injection
         * so we required to initiate all and pass to userrepo
         * but we are passing to viewmodel constructor
         * currently using viewbinding not passing any constructor
         * so creating authviewmodelFactory to pass required new instance factory
         * using viewmodel factory via passing the data
         * bad practise
         */
        val networkConnectionInterceptor= NetworkConnectionInterceptor(this)
        val api=MyApi(networkConnectionInterceptor=networkConnectionInterceptor)
        val db=AppDatabase(this)
        val respository = UserRepository(api,db)
        val factory=AuthViewModelFactory(repository = respository)
        
        
        
        
        
          @FormUrlEncoded
            @POST("login")
             fun userLoginOld(
                @Field("email") email: String,
                @Field("password") password: String
            ) : Call<ResponseBody> //ResponseBody
            
  
  Suspending function is centre of everything in Coroutines . 
  A Suspending function is simply a function that can be paused and resumed at a later time. 
  so these types of function can execute a long running 
  operation and wait for it to complete without blocking. As our function userLogin perform a network
  operation in api class and it's not returing as call but its returing us as response of 
  type authResponse  




What is Dependency Injection 
1. Loosely Coupled 
2. Easy Testing 

Class User {

private val appDb = AppDatabase() 

} // avoid to create object 
// A class User dependent on AppDatabase Class to avoid this we required Dependency Injection - difficult to mock existing code 

To Achieve this 
Class User( private val appDb = AppDatabase 
)  {

// rest of the code easy to mock 
in general 

}

KodeIn (Kotlin dependency Injection Framework)

Kodein is very simple and very useful dependency retrieval container .
It is very easy to use and configure 