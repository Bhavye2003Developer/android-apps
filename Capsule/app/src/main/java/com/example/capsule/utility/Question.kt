package com.example.capsule.utility

data class Question(
    val questionText: String,
    val options: Array<String>,
    val answer: String
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Question

        if (questionText != other.questionText) return false
        if (!options.contentEquals(other.options)) return false
        return answer == other.answer
    }

    override fun hashCode(): Int {
        var result = questionText.hashCode()
        result = 31 * result + options.contentHashCode()
        result = 31 * result + answer.hashCode()
        return result
    }
}
