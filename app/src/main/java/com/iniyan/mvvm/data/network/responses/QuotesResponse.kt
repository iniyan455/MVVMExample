package com.iniyan.mvvm.data.network.responses

import com.iniyan.mvvm.data.db.entities.Quote

data class QuotesResponse (
    val isSuccessful: Boolean,
    val quotes: List<Quote>
)