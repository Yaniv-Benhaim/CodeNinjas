package tech.gamedev.codeninjas.repo

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import tech.gamedev.codeninjas.other.Constants.CPLUSPLUS
import tech.gamedev.codeninjas.other.Constants.JAVA
import tech.gamedev.codeninjas.other.Constants.JAVASCRIPT
import tech.gamedev.codeninjas.other.Constants.KOTLIN
import tech.gamedev.codeninjas.other.Constants.SWIFT
import java.lang.Exception
import java.util.*

class LessonRepository {

    private val fireStoreCollectionRef = FirebaseFirestore.getInstance()
    private val userProgressRefJava = fireStoreCollectionRef.collection("users").document("lessons").collection("java")
    private val userProgressRefKotlin = fireStoreCollectionRef.collection("users").document("lessons").collection("kotlin")
    private val userProgressRefSwift = fireStoreCollectionRef.collection("users").document("lessons").collection("swift")
    private val userProgressRefJavascript = fireStoreCollectionRef.collection("users").document("lessons").collection("javascript")
    private val userProgressRefCplusplus = fireStoreCollectionRef.collection("users").document("lessons").collection("cplusplus")

    private val lessonCountRefJava = fireStoreCollectionRef.collection("lessons").document("java").collection("lessons")
    private val lessonCountRefKotlin = fireStoreCollectionRef.collection("lessons").document("kotlin").collection("lessons")
    private val lessonCountRefSwift = fireStoreCollectionRef.collection("lessons").document("swift").collection("lessons")
    private val lessonCountRefJavascript = fireStoreCollectionRef.collection("lessons").document("javascript").collection("lessons")
    private val lessonCountRefCplusplus = fireStoreCollectionRef.collection("lessons").document("cplusplus").collection("lessons")

    private val _userProgress = MutableLiveData<Int>()
    val userProgress: LiveData<Int> = _userProgress

    private val _amountOfLessons = MutableLiveData<Int>()
    val amountOfLessons: LiveData<Int> = _amountOfLessons

    fun getUserProgress(subject: String) {
        var lessonsFinished = 0
        try {
            CoroutineScope(Dispatchers.IO).launch {
                when (subject) {
                    KOTLIN.toLowerCase(Locale.ROOT) -> {
                        val userProgress = userProgressRefKotlin.get().await()
                        for (lessons in userProgress.documents) {
                            lessonsFinished++
                        }
                        _userProgress.postValue(lessonsFinished)
                    }

                    JAVA.toLowerCase(Locale.ROOT) -> {
                        val userProgress = userProgressRefJava.get().await()
                        for (lessons in userProgress.documents) {
                            lessonsFinished++
                        }
                        _userProgress.postValue(lessonsFinished)
                    }

                    CPLUSPLUS.toLowerCase(Locale.ROOT) -> {
                        val userProgress = userProgressRefCplusplus.get().await()
                        for (lessons in userProgress.documents) {
                            lessonsFinished++
                        }
                        _userProgress.postValue(lessonsFinished)
                    }

                    SWIFT.toLowerCase(Locale.ROOT) -> {
                        val userProgress = userProgressRefSwift.get().await()
                        for (lessons in userProgress.documents) {
                            lessonsFinished++
                        }
                        _userProgress.postValue(lessonsFinished)
                    }

                    JAVASCRIPT.toLowerCase(Locale.ROOT) -> {
                        val userProgress = userProgressRefJavascript.get().await()
                        for (lessons in userProgress.documents) {
                            lessonsFinished++
                        }
                        _userProgress.postValue(lessonsFinished)
                    }
                }
            }
        }catch (e: Exception) {
            Log.d("USERPROGRESS", e.message.toString())
        }

    }

    fun getAmountOfLessons(subject: String) {
        var amountOfLessons = 0
        try {
            CoroutineScope(Dispatchers.IO).launch {
                when (subject) {
                    KOTLIN.toLowerCase(Locale.ROOT) -> {
                        val lessonCollection = lessonCountRefKotlin.get().await()
                        for (lesson in lessonCollection.documents) {
                            amountOfLessons++
                        }
                        _amountOfLessons.postValue(amountOfLessons)
                    }

                    JAVA.toLowerCase(Locale.ROOT) -> {
                        val lessonCollection = lessonCountRefJava.get().await()
                        for (lesson in lessonCollection.documents) {
                            amountOfLessons++
                        }
                        _amountOfLessons.postValue(amountOfLessons)
                    }

                    CPLUSPLUS.toLowerCase(Locale.ROOT) -> {
                        val lessonCollection = lessonCountRefCplusplus.get().await()
                        for (lesson in lessonCollection.documents) {
                            amountOfLessons++
                        }
                        _amountOfLessons.postValue(amountOfLessons)
                    }

                    SWIFT.toLowerCase(Locale.ROOT) -> {
                        val lessonCollection = lessonCountRefSwift.get().await()
                        for (lesson in lessonCollection.documents) {
                            amountOfLessons++
                        }
                        _amountOfLessons.postValue(amountOfLessons)
                    }

                    JAVASCRIPT.toLowerCase(Locale.ROOT) -> {
                        val lessonCollection = lessonCountRefJavascript.get().await()
                        for (lesson in lessonCollection.documents) {
                            amountOfLessons++
                        }
                        _amountOfLessons.postValue(amountOfLessons)
                    }
                }
            }
        }catch (e: Exception) {
            Log.d("USERPROGRESS", e.message.toString())
        }

    }
}