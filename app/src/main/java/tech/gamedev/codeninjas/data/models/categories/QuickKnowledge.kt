package tech.gamedev.codeninjas.data.models.categories

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class QuickKnowledge (
        val subject: String,
        val videoUrl: String
        ) : Parcelable