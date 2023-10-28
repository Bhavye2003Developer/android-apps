package com.example.quotify

class Quotes {
    private val quotes = arrayOf(
        arrayOf("The only way to do great work is to love what you do.", "Steve Jobs"),
        arrayOf("Success is not final, failure is not fatal: It is the courage to continue that counts.", "Winston Churchill"),
        arrayOf("In the end, we will remember not the words of our enemies, but the silence of our friends.", "Martin Luther King Jr."),
        arrayOf("The only thing necessary for the triumph of evil is for good men to do nothing.", "Edmund Burke"),
        arrayOf("To be yourself in a world that is constantly trying to make you something else is the greatest accomplishment.", "Ralph Waldo Emerson"),
        arrayOf("The greatest glory in living lies not in never falling, but in rising every time we fall.", "Nelson Mandela"),
        arrayOf("Your time is limited, don't waste it living someone else's life.", "Steve Jobs"),
        arrayOf("In three words I can sum up everything I've learned about life: it goes on.", "Robert Frost"),
        arrayOf("The best way to predict the future is to create it.", "Peter Drucker"),
        arrayOf("Life is really simple, but we insist on making it complicated.", "Confucius"),
        arrayOf("The way to get started is to quit talking and begin doing.", "Walt Disney"),
        arrayOf("You miss 100% of the shots you don't take.", "Wayne Gretzky"),
        arrayOf("Believe you can and you're halfway there.", "Theodore Roosevelt")
    )

    fun getQuotes() : Array<Array<String>>{
        return quotes
    }
}