package tech.gamedev.codeninjas.ui.learn

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.firebase.ui.firestore.paging.FirestorePagingOptions
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import dagger.hilt.android.lifecycle.HiltViewModel
import tech.gamedev.codeninjas.data.models.lessons.LessonAndQuestion
import tech.gamedev.codeninjas.data.models.lessons.LessonCollectionLink
import tech.gamedev.codeninjas.repo.CreateNewLessonsRepo
import tech.gamedev.codeninjas.repo.LessonRepository
import javax.inject.Inject

@HiltViewModel
class LearnViewModel @Inject constructor(private val db: FirebaseFirestore, private val repoNewLesson: CreateNewLessonsRepo, private val lessonRepository: LessonRepository) : ViewModel() {

    private val _weapon = MutableLiveData<String>()
    val subject: LiveData<String> = _weapon

    private val _currentProgressInSteps = MutableLiveData<Int>()
    val currentProgressInSteps: LiveData<Int> = _currentProgressInSteps

    private val totalAmountOfLessons = lessonRepository.amountOfLessons
    val userProgress = lessonRepository.userProgress
    private fun getUserProgress(subject: String) = lessonRepository.getUserProgress(subject)
    private fun getAmountOfLessons(subject: String) = lessonRepository.getAmountOfLessons(subject)
    fun createNewLesson() = repoNewLesson.addNewLesson()

    fun setWeapon(weapon: String) {
        _weapon.value = weapon
        getUserProgress(subject.value!!)
        getAmountOfLessons(subject.value!!)
        getProgress()
    }

    private fun getProgress() {
        if(userProgress.value != null && userProgress.value!! > 0){
            if (totalAmountOfLessons.value != null && totalAmountOfLessons.value!! > 0) {
                val curProgress = userProgress.value!!
                val totalLessons = totalAmountOfLessons.value!!
                if(curProgress == totalLessons) {

                } else {
                    val percentage: Int = (totalLessons / curProgress) * 10
                    _currentProgressInSteps.value = percentage
                    Log.d("CALC", percentage.toString())
                }

            }
        }
        _currentProgressInSteps.value = 1
    }

    fun getLessons() = lessonRepository.getLessons(subject.value!!)
    fun getSpecificLessons(lessonCollection: LessonCollectionLink) = lessonRepository.getSpecificLessonList(subject.value!!, lessonCollection)

    fun provideUpdatedOptions(subject: String): FirestorePagingOptions.Builder<LessonCollectionLink> {
        val query = db.collection("lessons").document(subject).collection("modules").orderBy("lesson_id", Query.Direction.ASCENDING)
        val config = PagedList.Config.Builder()
            .setInitialLoadSizeHint(30)
            .setPageSize(4)
            .build()

        return FirestorePagingOptions.Builder<LessonCollectionLink>()
            .setQuery(query, config, LessonCollectionLink::class.java)

    }

    fun provideSpecificLessonOptions(args: String): FirestorePagingOptions.Builder<LessonAndQuestion> {
        val query = db
            .collection("lessons")
            .document(subject.value!!.toLowerCase())
            .collection("modules")
            .document(args)
            .collection("lessons")
            .orderBy("lessonId")

        val config = PagedList.Config.Builder()
            .setInitialLoadSizeHint(30)
            .setPageSize(4)
            .build()

        return FirestorePagingOptions.Builder<LessonAndQuestion>()
            .setQuery(query, config, LessonAndQuestion::class.java)
    }
}