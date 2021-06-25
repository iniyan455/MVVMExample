package com.iniyan.mvvm.util

import java.io.IOException
import java.lang.Exception


class ApiException(message: String) : Exception(message)

class NoInternetException(message: String) : IOException(message)