package tech.gamedev.codeninjas.repo

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import tech.gamedev.codeninjas.data.models.user.User
import tech.gamedev.codeninjas.other.Constants.LEVEL_ONE
import tech.gamedev.codeninjas.other.Constants.LOGIN_TAG

class LoginRepository() {
    private val db = FirebaseFirestore.getInstance()
    private val auth = FirebaseAuth.getInstance()
    private val currentUser = auth.currentUser!!.email
    private val userCollectionRef = db.collection("users")


    private val _user = MutableLiveData<User>()
    val user: LiveData<User> = _user

    fun checkIfUserExists() {
        userCollectionRef.document(currentUser.toString()).get()
            .addOnSuccessListener {
                if(it.data != null) {
                    Log.d(LOGIN_TAG, "USER FOUND! : ${it.data}")
                    _user.value = it.toObject<User>()
                } else{
                    Log.d(LOGIN_TAG, "No such document")
                    createNewUser()
                }
            }
            .addOnFailureListener {
                Log.d(LOGIN_TAG, "get failed with ", it)
            }
    }

    private fun createNewUser() {
        val newUser = User(email = currentUser!!,level = LEVEL_ONE)
        _user.value = newUser
        Log.d(LOGIN_TAG, "USER LIVEDATA SET: ${_user.value}")
        userCollectionRef.document(currentUser).set(newUser)
    }

    fun getCurrentUser() = auth.currentUser?.uid
}