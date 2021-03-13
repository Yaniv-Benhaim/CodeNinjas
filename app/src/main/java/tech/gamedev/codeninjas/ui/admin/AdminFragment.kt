package tech.gamedev.codeninjas.ui.admin

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_admin.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import tech.gamedev.codeninjas.R
import tech.gamedev.codeninjas.data.models.retrofit.NotificationModel
import tech.gamedev.codeninjas.data.models.retrofit.PushNotification
import tech.gamedev.codeninjas.data.models.retrofit.RetrofitInstance
import tech.gamedev.codeninjas.other.Constants.NOTIFICATION_TOPIC
import java.lang.Exception

class AdminFragment : Fragment(R.layout.activity_admin){

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnSendNotification.setOnClickListener {

            val title = etTitle.text.toString()
            val message = etMessage.text.toString()
            val token = etToken.text.toString()

            if(title.isNotEmpty() && message.isNotEmpty()){
                PushNotification(
                    NotificationModel(title,message),
                    NOTIFICATION_TOPIC
                ).also {
                    sendNotification(it)
                }
            }
        }
    }
    }



private fun sendNotification(notification: PushNotification) = CoroutineScope(Dispatchers.IO).launch {
    try {
        val response = RetrofitInstance.api.postNotification(notification)
        if(response.isSuccessful) {
            Log.d("NOTIFICATION", "Response: ${Gson().toJson(response)}")
        } else {
            Log.e("NOTIFICATION", response.errorBody().toString())
        }
    } catch(e: Exception) {
        Log.e("NOTIFICATION", e.toString())
    }

}
