package tech.gamedev.codeninjas.ui.learn

import android.util.Log
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import tech.gamedev.codeninjas.data.models.LessonCollectionLink
import tech.gamedev.codeninjas.repo.CreateNewLessonsRepo
import tech.gamedev.codeninjas.repo.LessonRepository
import tech.gamedev.codeninjas.repo.LoginRepository

class LearnViewModel @ViewModelInject constructor(private val repoNewLesson: CreateNewLessonsRepo, private val lessonRepository: LessonRepository, private val loginRepository: LoginRepository) : ViewModel() {

    private val _weapon = MutableLiveData<String>()
    val subject: LiveData<String> = _weapon

    val lessons = lessonRepository.lessons
    val specificLessons = lessonRepository.specificLessons

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
}