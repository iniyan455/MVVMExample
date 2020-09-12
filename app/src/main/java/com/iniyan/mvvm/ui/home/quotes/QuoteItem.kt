package com.iniyan.mvvm.ui.home.quotes

import com.iniyan.mvvm.R
import com.iniyan.mvvm.databinding.ItemQuoteBinding
import com.xwray.groupie.databinding.BindableItem
import com.iniyan.mvvm.data.db.entities.Quote

class QuoteItem(
    private val quote: Quote
) : BindableItem<ItemQuoteBinding>(){

    override fun getLayout() = R.layout.item_quote

    override fun bind(viewBinding: ItemQuoteBinding, position: Int) {
        viewBinding.setQuote(quote)
    }
}