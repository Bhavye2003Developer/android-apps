package com.example.androidtrivia

class Questions {
    private var index = 0
    private val allQuestions = arrayListOf(
        Question(
            questionText = "Android's primary programming language?",
            answer = "Java", options = arrayListOf("Java", "Swift", "C#", "Python")
        ),
        Question(
            questionText = "XML file for defining UI layout?",
            answer = "Activity", options = arrayListOf("Manifest", "Gradle", "Activity", "Layout")
        ),
        Question(
            questionText = "Manages app navigation and activities?",
            answer = "Intent", options = arrayListOf("Intent", "Receiver", "Activity", "Provider")
        ),
        Question(
            questionText = "Purpose of an Android emulator?",
            answer = "Simulate", options = arrayListOf("Test", "Build", "Simulate", "Design")
        ),
        Question(
            questionText = "AndroidX for?",
            answer = "Older versions",
            options = arrayListOf("Services", "Older versions", "Purchases", "Native code")
        ),
        Question(
            questionText = "Gradle's role?",
            answer = "Build", options = arrayListOf("IDE", "Editor", "Build", "Emulator")
        ),
        Question(
            questionText = "Background processing without UI?",
            answer = "Service", options = arrayListOf("Service", "Activity", "Fragment", "Intent")
        ),
        Question(
            questionText = "APK stands for?",
            answer = "Package", options = arrayListOf("Package", "Patch", "Program", "Key")
        ),
        Question(
            questionText = "Best for a scrolling list of items?",
            answer = "RecyclerView",
            options = arrayListOf("FrameLayout", "ConstraintLayout", "RecyclerView", "GridLayout")
        ),
        Question(
            questionText = "ProGuard use?",
            answer = "Optimize", options = arrayListOf("Optimize", "Test", "Analyze", "Document")
        )
    )

    init {
        allQuestions.shuffle()
    }

    fun getNextQuestion(): Question {
        val question = allQuestions[index++]
        question.options.shuffle()
        return question
    }
}