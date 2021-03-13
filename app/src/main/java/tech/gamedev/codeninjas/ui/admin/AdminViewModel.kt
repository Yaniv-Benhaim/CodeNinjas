package tech.gamedev.codeninjas.ui.admin

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import tech.gamedev.codeninjas.data.models.retrofit.PushNotification
import tech.gamedev.codeninjas.repo.NotificationRepo
import javax.inject.Inject

@HiltViewModel
class AdminViewModel @Inject constructor(private val notificationRepo: NotificationRepo) : ViewModel() {

    fun postNotification(pushNotification: PushNotification) = viewModelScope.launch(Dispatchers.IO) {
        notificationRepo.sendNotification(pushNotification)
    }



}