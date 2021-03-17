package tech.gamedev.codeninjas.repo

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import tech.gamedev.codeninjas.data.models.lessons.LessonAndQuestion
import tech.gamedev.codeninjas.data.models.lessons.LessonCollectionLink
import tech.gamedev.codeninjas.data.models.categories.QuickKnowledge
import tech.gamedev.codeninjas.other.Constants.CPLUSPLUS
import tech.gamedev.codeninjas.other.Constants.JAVA
import tech.gamedev.codeninjas.other.Constants.JAVASCRIPT
import tech.gamedev.codeninjas.other.Constants.KOTLIN
import tech.gamedev.codeninjas.other.Constants.SWIFT
import java.util.*
import kotlin.Exception
import kotlin.collections.ArrayList

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

    private val javaQuickKnowledgeRef = fireStoreCollectionRef.collection("quick_knowledge").document(JAVA.toLowerCase(Locale.ROOT)).collection("categories")
    private val kotlinQuickKnowledgeRef = fireStoreCollectionRef.collection("quick_knowledge").document(KOTLIN.toLowerCase(Locale.ROOT)).collection("categories")
    private val javascriptQuickKnowledgeRef = fireStoreCollectionRef.collection("quick_knowledge").document(JAVASCRIPT.toLowerCase(Locale.ROOT)).collection("categories")
    private val swiftQuickKnowledgeRef = fireStoreCollectionRef.collection("quick_knowledge").document(SWIFT.toLowerCase(Locale.ROOT)).collection("categories")
    private val cplusplusQuickKnowledgeRef = fireStoreCollectionRef.collection("quick_knowledge").document(CPLUSPLUS.toLowerCase(Locale.ROOT)).collection("categories")

    private val _userProgress = MutableLiveData<Int>()
    val userProgress: LiveData<Int> = _userProgress

    private val _amountOfLessons = MutableLiveData<Int>()
    val amountOfLessons: LiveData<Int> = _amountOfLessons

    private val _lessons = MutableLiveData<ArrayList<LessonCollectionLink>>()
    val lessons: LiveData<ArrayList<LessonCollectionLink>> = _lessons

    private val _specificLessons = MutableLiveData<ArrayList<LessonAndQuestion>>()
    val specificLessons: LiveData<ArrayList<LessonAndQuestion>> = _specificLessons

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

    /*
    Get amount of lessons Flow
    GET -> lessons -> java -> lessons -> lesson1 -> java_introduction -> documents

     */

    fun getLessons(type: String) = CoroutineScope(Dispatchers.IO).launch {
        try {
            when(type){
                JAVA -> getLessonsFromType(JAVA)
                KOTLIN -> getLessonsFromType(KOTLIN)
                CPLUSPLUS -> getLessonsFromType(CPLUSPLUS)
                JAVASCRIPT -> getLessonsFromType(JAVASCRIPT)
                SWIFT -> getLessonsFromType(SWIFT)
            }
        } catch (e: Exception) {
            Log.d("lessons", e.message.toString())
        }
    }

    private suspend fun getLessonsFromType(type: String) {
        try {
            val lessonCollection = when(type) {
                JAVA -> lessonCountRefJava.get().await()
                KOTLIN -> lessonCountRefKotlin.get().await()
                CPLUSPLUS -> lessonCountRefCplusplus.get().await()
                JAVASCRIPT -> lessonCountRefJavascript.get().await()
                SWIFT -> lessonCountRefSwift.get().await()
                else -> lessonCountRefJava.get().await()
            }

            _lessons.value = ArrayList()
            for (document in lessonCollection.documents) {
                val lesson = document.toObject<LessonCollectionLink>()
                _lessons.value!!.add(lesson!!)
            }

        } catch (e: Exception) {
            Log.d("lessons", e.message.toString())
        }
    }

    fun getSpecificLessonList(type: String, lessonCollection: LessonCollectionLink) = CoroutineScope(Dispatchers.IO).launch {

        val specificLessons = when(type) {
            JAVA -> lessonCountRefJava
                    .document(lessonCollection.lesson_id)
                    .collection(lessonCollection.collection_link)
                    .get()
                    .await()

            KOTLIN -> lessonCountRefKotlin
                    .document(lessonCollection.lesson_id)
                    .collection(lessonCollection.collection_link)
                    .get()
                    .await()

            CPLUSPLUS -> lessonCountRefCplusplus
                    .document(lessonCollection.lesson_id)
                    .collection(lessonCollection.collection_link)
                    .get()
                    .await()

            JAVASCRIPT -> lessonCountRefJavascript
                    .document(lessonCollection.lesson_id)
                    .collection(lessonCollection.collection_link)
                    .get()
                    .await()

            SWIFT -> lessonCountRefSwift
                    .document(lessonCollection.lesson_id)
                    .collection(lessonCollection.collection_link)
                    .get()
                    .await()

            else -> lessonCountRefJava.document(lessonCollection.lesson_id)
                    .collection(lessonCollection.collection_link)
                    .get()
                    .await()
        }

        _specificLessons.value = ArrayList()
        for (document in specificLessons) {
            val lesson = document.toObject<LessonAndQuestion>()
            _specificLessons.value!!.add(lesson)
        }
    }

    suspend fun getQuickKnowledge(language: String, topic: String): QuickKnowledge? {
        return when(language) {
            KOTLIN -> kotlinQuickKnowledgeRef.document(topic.toUpperCase()).get().await().toObject(
                QuickKnowledge::class.java)
            JAVA -> javaQuickKnowledgeRef.document(topic.toUpperCase()).get().await().toObject(
                QuickKnowledge::class.java)
            CPLUSPLUS -> cplusplusQuickKnowledgeRef.document(topic.toUpperCase()).get().await().toObject(
                QuickKnowledge::class.java)
            JAVASCRIPT -> javascriptQuickKnowledgeRef.document(topic.toUpperCase()).get().await().toObject(
                QuickKnowledge::class.java)
            SWIFT -> swiftQuickKnowledgeRef.document(topic.toUpperCase()).get().await().toObject(
                QuickKnowledge::class.java)
            else -> null
        }
    }
}