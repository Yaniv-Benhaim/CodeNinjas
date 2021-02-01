package tech.gamedev.codeninjas.data.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class LessonAndQuestion(
    val lessonId: Int = 0,
    val lessonTitle: String = "",
    val lessonFinished: Boolean = false,
    val lessonContent: String = "",
    val lessonHasImg: Boolean = false,
    val imgUrlOne: String = "",
    val lessonHasTwoImg: Boolean = false,
    val questionOne: String = "",
    val questionOneIsMultipleChoice: Boolean = true,
    val questionOneCorrectAnswer: String = "",
    val answerA: String = "",
    val answerB: String = "",
    val answerC: String = "",
    val answerD: String = "",
    val experienceGained: Long = 10
) : Parcelable
