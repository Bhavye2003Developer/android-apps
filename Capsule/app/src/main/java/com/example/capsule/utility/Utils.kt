package com.example.capsule.utility

import com.example.capsule.fragments.NotesFragment
import com.example.capsule.fragments.QuizFragment
import com.example.capsule.fragments.VideoFragment

class Utils {
    companion object {
        val fragments = arrayOf(VideoFragment(), NotesFragment(), QuizFragment())
        val fragmentNames = arrayOf("Video", "Notes", "Quiz")
        const val pdf =
            "https://ncert.nic.in/ncerts/l/gesc111.pdf"  // sample pdf to show in webview
    }

    class Quiz {
        private var score: Int = 0
        private val bioQuestions = arrayOf(
            Question(
                "What is the primary function of blood in the human body?",
                arrayOf(
                    "Transporting oxygen",
                    "Digesting food",
                    "Producing energy",
                    "Filtering waste"
                ),
                "Transporting oxygen"
            ),

            Question(
                "Which component of blood is responsible for carrying oxygen?",
                arrayOf("Plasma", "Red blood cells", "White blood cells", "Platelets"),
                "Red blood cells"
            ),

            Question(
                "What is the liquid part of blood that carries nutrients, hormones, and waste products?",
                arrayOf("Red blood cells", "Plasma", "Platelets", "White blood cells"),
                "Plasma"
            ),

            Question(
                "Which blood type is known as the 'universal donor'?",
                arrayOf("A", "B", "AB", "O"),
                "O"
            ),

            Question(
                "What is the function of platelets in the blood?",
                arrayOf(
                    "Transporting oxygen",
                    "Blood clotting",
                    "Fighting infections",
                    "Carrying nutrients"
                ),
                "Blood clotting"
            ),

            Question(
                "What is the process by which blood forms clots to stop bleeding?",
                arrayOf("Oxygenation", "Coagulation", "Filtration", "Digestion"),
                "Coagulation"
            ),

            Question(
                "Which blood vessels carry blood away from the heart?",
                arrayOf("Veins", "Arteries", "Capillaries", "Venules"),
                "Arteries"
            ),

            Question(
                "What is the average lifespan of a red blood cell in the human body?",
                arrayOf("30 days", "60 days", "90 days", "120 days"),
                "120 days"
            ),

            Question(
                "Which organ produces most of the body's blood cells?",
                arrayOf("Liver", "Spleen", "Bone marrow", "Kidneys"),
                "Bone marrow"
            ),

            Question(
                "What is the name of the condition characterized by a deficiency of red blood cells or hemoglobin?",
                arrayOf("Leukemia", "Anemia", "Thrombocytosis", "Hemophilia"),
                "Anemia"
            )
        )

        var questionIndex = 0

        init {
            bioQuestions.shuffle()
        }

        fun getQuestion(): Question? {
            if (questionIndex >= bioQuestions.size) return null
            return bioQuestions[questionIndex++]
        }

        fun incrementScore() = ++score

        fun getTotalScore() = score
    }
}