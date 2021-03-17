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
import kotlinx.coroutines.withContext
import tech.gamedev.codeninjas.data.models.battle.BattleQuestion
import tech.gamedev.codeninjas.data.models.user.User
import javax.inject.Inject

class BattleRepo @Inject constructor() {
    private val userRef = FirebaseFirestore.getInstance().collection("users")
    private val javaQuestionRef = FirebaseFirestore.getInstance().collection("battle_questions").document("java").collection("java")
    private val _dummyUsers = MutableLiveData<ArrayList<User>>()
    val dummyUsers: LiveData<ArrayList<User>> = _dummyUsers

    private val _javaQuestions = MutableLiveData<ArrayList<BattleQuestion>>()
    val javaQuestions : LiveData<ArrayList<BattleQuestion>> = _javaQuestions

    fun getDummyUsersForBattle() = CoroutineScope(Dispatchers.IO).launch {
        try {
            withContext(Dispatchers.Main){
            _dummyUsers.value = ArrayList()
            }
            val users = userRef.whereEqualTo("dummyUser", true).get().await()
            for (document in users.documents) {
                val user = document.toObject<User>()

                    withContext(Dispatchers.Main) {
                        _dummyUsers.value!!.add(user!!)
                        Log.d("BATTLE", user.userName)
                    }


            }
        }catch (e:Exception) {
            Log.d("BATTLE", "ERROR: ${e.message}")
        }

    }

    fun getDummyUsersListener() {
        userRef.whereEqualTo("dummyUser", true).addSnapshotListener { querySnapshot, error ->

            error?.let {
                Log.d("BATTLE", it.message.toString())
                return@addSnapshotListener
            }
            querySnapshot?.let {
                _dummyUsers.value = ArrayList()
                for (document in it){
                    val user = document.toObject<User>()
                    Log.d("BATTLE", user.userName)
                    _dummyUsers.value!!.add(user)
                }
            }
        }
    }

    fun getBattleQuestionsJava() = CoroutineScope(Dispatchers.IO).launch {
        try {
            withContext(Dispatchers.Main) {
                _javaQuestions.value = ArrayList()
            }
            val questions = javaQuestionRef.get().await()
            for (document in questions.documents) {
                withContext(Dispatchers.Main) {
                    val question = document.toObject<BattleQuestion>()
                    _javaQuestions.value!!.add(question!!)
                }
            }
        }catch (e: Exception) {
            Log.d("BATTLE", e.message.toString())
        }
    }

    fun getJavaQuestionsListener() {
        javaQuestionRef.addSnapshotListener { querySnapshot, error ->

            error?.let {
                Log.d("BATTLE", it.message.toString())
                return@addSnapshotListener
            }
            querySnapshot?.let {
                _javaQuestions.value = ArrayList()
                for (document in it){
                    val question = document.toObject<BattleQuestion>()
                    Log.d("BATTLE", question.question)
                    _javaQuestions.value!!.add(question)
                }
            }
        }
    }

    fun giveUpBattle() {
        //TODO: IMPLEMENT GIVE UP AND UPDATE USER INFO WITH LOSS/WIN
    }
}

