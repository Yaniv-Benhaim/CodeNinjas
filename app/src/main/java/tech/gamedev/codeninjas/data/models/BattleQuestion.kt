package tech.gamedev.codeninjas.data.models

data class BattleQuestion(
        val question: String = "",
        val questionImg: Boolean = false,
        val questionImgUrl: String = "",
        val correctAnswer: String = "",
        val answerA: String = "",
        val answerB: String = "",
        val answerC: String = "",
        val answerD: String = "",
        val multipleChoice: Boolean = false,
        val questionLvl: Long = 1
)
