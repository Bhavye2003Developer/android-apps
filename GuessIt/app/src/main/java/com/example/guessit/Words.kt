package com.example.guessit

class Words {
    private val words: ArrayList<String> = arrayListOf(
        "apple",
        "banana",
        "cat",
        "dog",
        "elephant",
        "frog",
        "guitar",
        "house",
        "island",
        "jazz",
        "kite",
        "lion",
        "moon",
        "nest",
        "orange",
        "pencil",
        "queen",
        "rabbit",
        "sun",
        "tree"
    )
    private var size: Int = words.size

    fun getWord(): String{
        if (size>0){
            val randomIndex = (0..<size).random()
            val word = words[randomIndex]
            words.removeAt(randomIndex)
            --size
            return word
        }
        return ""
    }
}