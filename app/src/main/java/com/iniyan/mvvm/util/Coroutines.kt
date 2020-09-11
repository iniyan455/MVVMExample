package net.simplifiedcoding.mvvmsampleapp.util

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

object Coroutines {

    /**
     * Unit is equivalent to void java
     */
    fun main(work: suspend (() -> Unit)) =

        CoroutineScope(Dispatchers.Main).launch {
            // Dispatchers.Main - Main Thread there are four options -Default,Main,IO
            //Launch returns a job
            work()
        }

    fun io(work: suspend (() -> Unit)) =
        CoroutineScope(Dispatchers.IO).launch {
            work()
        }

}