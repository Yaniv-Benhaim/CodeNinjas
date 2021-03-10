package tech.gamedev.codeninjas.data.models.lessonextensions

data class Lesson(
        val lessonId: Int = 0,
        val lessonTitle: String = "",
        val lessonFinished: Boolean = false,
        val lessonContent: String = "",
        val lessonHasImg: Boolean = false,
        val imgUrlOne: String = "",
        val lessonHasTwoImg: Boolean = false,
        val experienceGained: Long = 10,
        val summary: String = "",
        val hintStart: String = "",
        val hintEnd: String = "",
        val imgUrlTwo: String = "",
        val hasVideo: Boolean = false,
        val videoUrl: String = ""
) : BaseLesson()
