package tech.gamedev.codeninjas.data.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class User(
    val userName: String = "",
    val email: String = "",
    val level: String = "",
    val experience: Long = 0,
    val battlesWon: Long = 0,
    val battlesLost: Long = 0,
    val isDummyUser: Boolean = false,
    val profileImg: String = ""
): Parcelable
