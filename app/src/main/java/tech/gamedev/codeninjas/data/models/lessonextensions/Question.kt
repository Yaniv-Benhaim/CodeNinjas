package tech.gamedev.codeninjas.data.models.lessonextensions

data class Question(
        val lessonId: Int = 0,
        val questionOne: String = "",
        val questionOneIsMultipleChoice: Boolean = true,
        val questionOneCorrectAnswer: String = "",
        val answerA: String = "",
        val answerB: String = "",
        val answerC: String = "",
        val answerD: String = "",
        val questionHasImg: Boolean = false,
        val imgUrl: String = ""
) : BaseLesson()
