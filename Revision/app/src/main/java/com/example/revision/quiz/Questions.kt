package com.example.revision.quiz

class Questions {

    private val questions: Array<Question> = arrayOf(
        Question("What is the primary function of an Android 'ViewModel'?", arrayOf(
            "UI", "Data", "Logic", "Network"
        ), "Data"),

        Question("What does the AndroidManifest.xml file primarily declare?", arrayOf(
            "Layout", "Components", "Styles", "Strings"
        ), "Components"),

        Question("Which layout is best for scrollable lists?", arrayOf(
            "ScrollView", "FrameLayout", "RelativeLayout", "RecyclerView"
        ), "RecyclerView"),

        Question("Which component performs background tasks in Android?", arrayOf(
            "Activity", "Service", "Intent", "Fragment"
        ), "Service"),

        Question("What does an Intent represent in Android?", arrayOf(
            "Data", "Logic", "Request", "UI"
        ), "Request")
    )

    private val questionsIndex = arrayListOf(1,2,3,4,5)
    private var countNumQuestions = 0
    private var score = 0

    fun getQuestion() : Question{
        if (countNumQuestions==3){
            return Question("", arrayOf(), "") // empty object
        }
        ++countNumQuestions
        val questionIndex = questionsIndex.random()
        questionsIndex.remove(questionIndex)
        return questions[questionIndex - 1]
    }

    fun incrementScore(){
        ++score
    }

    fun isWon() : Boolean{
        return score==3
    }
}