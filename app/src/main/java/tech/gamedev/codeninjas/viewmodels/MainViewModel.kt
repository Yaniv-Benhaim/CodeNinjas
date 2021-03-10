package tech.gamedev.codeninjas.viewmodels

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import tech.gamedev.codeninjas.data.models.BattleQuestion
import tech.gamedev.codeninjas.data.models.User
import tech.gamedev.codeninjas.repo.BattleRepo
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val battleRepo: BattleRepo): ViewModel() {

    private val _weapon = MutableLiveData<String>()
    val weapon: LiveData<String> = _weapon

    val dummyUsersForBattle = battleRepo.dummyUsers
    val javaQuestions = battleRepo.javaQuestions

    private val _topScores = MutableLiveData<ArrayList<User>>()
    val topScores: LiveData<ArrayList<User>> = _topScores

    fun setWeapon(weapon: String) {
        _weapon.value = weapon
    }

    fun getDummyUsersForBattle() = battleRepo.getDummyUsersListener()
    fun getJavaQuestions() = battleRepo.getJavaQuestionsListener()


    fun getFiveRandomJavaQuestions(): List<BattleQuestion> {
        val questions = javaQuestions.value
        questions!!.shuffle()
        return listOf(questions[0], questions[1], questions[2], questions[3], questions[4])
    }

    fun giveUpBattle() = battleRepo.giveUpBattle()

    fun getTopScorers() {
        val users = dummyUsersForBattle.value
        val sortedList = users?.sortedWith(compareBy { it.battlesWon })
        _topScores.value = ArrayList()
        _topScores.value!!.addAll(sortedList!!)
    }
}
