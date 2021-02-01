package tech.gamedev.codeninjas.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {

    private val _weapon = MutableLiveData<String>()
    val weapon: LiveData<String> = _weapon

    fun setWeapon(weapon: String) {
        _weapon.value = weapon
    }
}