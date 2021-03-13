package tech.gamedev.codeninjas.data.models.retrofit

import tech.gamedev.codeninjas.data.models.retrofit.NotificationModel

data class PushNotification(
    val data: NotificationModel,
    val to: String
)
