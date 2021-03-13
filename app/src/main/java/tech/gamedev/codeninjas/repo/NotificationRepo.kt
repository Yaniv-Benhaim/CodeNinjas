package tech.gamedev.codeninjas.repo

import android.util.Log
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import tech.gamedev.codeninjas.data.models.retrofit.PushNotification
import tech.gamedev.codeninjas.data.models.retrofit.RetrofitInstance
import tech.gamedev.codeninjas.other.Constants.NOTIFICATION_TAG

class NotificationRepo {

    fun sendNotification(notification: PushNotification) = CoroutineScope(Dispatchers.IO).launch {

        try {
            val response = RetrofitInstance.api.postNotification(notification)
            if(response.isSuccessful) {
                Log.d(NOTIFICATION_TAG, "Response SUCCESS: ${
                    Gson().toJson(response.message())}")
            } else {
                Log.d(NOTIFICATION_TAG, "ERROR: ${
                    response.errorBody()}")
            }
        } catch (e: Exception) {
            Log.d(NOTIFICATION_TAG, e.toString())
        }

    }
}