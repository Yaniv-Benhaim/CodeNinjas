package tech.gamedev.codeninjas.data.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class LessonCollectionLink(
        val collection_link: String = "",
        val module_title: String = "",
        val lesson_id: String = ""
) : Parcelable
