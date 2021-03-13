package tech.gamedev.codeninjas.ui.splash

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import tech.gamedev.codeninjas.repo.LoginRepository
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val loginRepo: LoginRepository): ViewModel() {

    val user = loginRepo.user

    fun checkIfUserExists() = viewModelScope.launch(Dispatchers.IO) {  loginRepo.checkIfUserExists() }
    fun getCurrentUser() = loginRepo.getCurrentUser()
}