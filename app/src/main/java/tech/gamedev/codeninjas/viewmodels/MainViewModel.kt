package tech.gamedev.codeninjas.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {

    private val _weapon = MutableLiveData<String>()
    val weapon = _weapon

    fun setWeapon(weapon: String) {
        _weapon.value = weapon
    }
}