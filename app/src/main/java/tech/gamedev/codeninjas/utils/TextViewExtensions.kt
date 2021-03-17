package tech.gamedev.codeninjas.utils

import android.widget.TextView
import tech.gamedev.codeninjas.data.models.lessons.LessonAndQuestion

fun TextView.getFirstText(model: LessonAndQuestion) {
    this.text = model.lessonContent.split("<img>")[0].replace("_n", "\n")
}

fun TextView.getSecondText(model: LessonAndQuestion){
    this.text = model.lessonContent.split("<img>")[1].replace("_n", "\n")
}

fun TextView.textContainsImage(model: LessonAndQuestion) = model.lessonContent.contains("<img>")