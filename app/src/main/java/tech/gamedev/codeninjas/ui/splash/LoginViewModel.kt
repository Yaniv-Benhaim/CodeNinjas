package tech.gamedev.codeninjas.ui.splash

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import tech.gamedev.codeninjas.repo.LoginRepository

class LoginViewModel @ViewModelInject constructor(private val loginRepo: LoginRepository): ViewModel() {

    val user = loginRepo.user

    fun checkIfUserExists() = loginRepo.checkIfUserExists()
}