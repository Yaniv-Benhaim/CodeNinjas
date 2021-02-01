package tech.gamedev.codeninjas.data.models

data class User(
    val userName: String = "",
    val email: String = "",
    val level: String = "",
    val experience: Long = 0,
    val battlesWon: Long = 0,
    val battlesLost: Long = 0
)
