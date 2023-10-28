package com.example.quotify

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class QuoteViewModel : ViewModel() {
    private val quotes: Array<Array<String>> = Quotes().getQuotes()
    private var currentIndex: Int = 0

    private val _quoteText: MutableLiveData<String> = MutableLiveData("")
    val quoteText: LiveData<String>
        get() = _quoteText

    private val _quoteAuthor: MutableLiveData<String> = MutableLiveData("")
    val quoteAuthor: LiveData<String>
        get() = _quoteAuthor

    init {
        quotes.shuffle()
        setCurrentContent(0)
    }

    fun nextQuote(){
        ++currentIndex
        setCurrentContent(currentIndex%quotes.size)
    }

    fun prevQuote(){
        if (currentIndex>0 && currentIndex<=quotes.size){
            --currentIndex
        }
        else{
            currentIndex = quotes.size - 2
        }
        setCurrentContent(currentIndex)
    }

    private fun setCurrentContent(index:Int){
        _quoteText.value = quotes.elementAt(index)[0]
        _quoteAuthor.value = quotes.elementAt(index)[1]
    }
}