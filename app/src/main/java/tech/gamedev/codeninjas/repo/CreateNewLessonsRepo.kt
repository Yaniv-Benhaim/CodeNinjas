package tech.gamedev.codeninjas.repo

import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import tech.gamedev.codeninjas.data.models.LessonAndQuestion

class CreateNewLessonsRepo {
    private val fireStoreCollectionRef = FirebaseFirestore.getInstance()
    private val lessonRef = fireStoreCollectionRef.collection("lessons").document("java")

    fun addNewLesson() = CoroutineScope(Dispatchers.IO).launch {

        val newLesson = LessonAndQuestion(
                1,
                "Introduction to Java",
                false,
                "Java is a high level, modern programming language designed in the early 1990s by Sun Microsystems, and currently owned by Oracle. Java is Platform Independent, which means that you only need to write the program once to be able to run it on a number of different platforms! Java is portable, robust, and dynamic, with the ability to fit the needs of virtually any type of application.",
                false,
                "",
                false,
                "To distribute your application to different platforms, how many Java versions do you need to create?",
                true,
                "Just one version",
                "One for each platform",
                "Just one version",
                "Depends on the version of Java",
                "Two versions",
                10
        )





        lessonRef.collection("lessons").document("lesson1").set(newLesson)
    }
}