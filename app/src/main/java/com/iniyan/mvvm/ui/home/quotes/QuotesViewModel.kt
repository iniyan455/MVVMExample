package com.iniyan.mvvm.ui.home.quotes

import androidx.lifecycle.ViewModel;
import com.iniyan.mvvm.data.repository.QuotesRepository
import com.iniyan.mvvm.util.lazyDeferred

class QuotesViewModel(
    repository: QuotesRepository
) : ViewModel() {

    val quotes by lazyDeferred {
        repository.getQuotes()
    }
}
