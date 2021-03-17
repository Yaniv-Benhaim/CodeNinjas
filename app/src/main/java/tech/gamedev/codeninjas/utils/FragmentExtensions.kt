package tech.gamedev.codeninjas.utils

import android.widget.Toast
import androidx.fragment.app.Fragment
import tech.gamedev.codeninjas.other.Constants.CPLUSPLUS
import tech.gamedev.codeninjas.other.Constants.JAVA
import tech.gamedev.codeninjas.other.Constants.JAVASCRIPT
import tech.gamedev.codeninjas.other.Constants.KOTLIN
import tech.gamedev.codeninjas.other.Constants.SWIFT
import tech.gamedev.codeninjas.ui.home.HomeFragment


fun Fragment.setToast(message: String) {
    Toast.makeText(this.requireContext(), message, Toast.LENGTH_SHORT).show()
}

fun HomeFragment.getQuickKnowledge(subject: String): List<String> {

    return when(subject) {
        JAVA.toLowerCase() -> listOf("FUNCTIONS", "LOOPS", "Variables", "IF", "Classes", "Objects", "Casting", "Threads", "Arrays", "Try")
        KOTLIN.toLowerCase() -> listOf("FUNCTIONS", "LOOPS", "Variables", "IF", "Classes", "Objects", "Casting", "Threads", "Arrays", "Try")
        SWIFT.toLowerCase() -> listOf("FUNCTIONS", "LOOPS", "Variables", "IF", "Classes", "Objects", "Casting", "Threads", "Arrays", "Try")
        CPLUSPLUS.toLowerCase() -> listOf("FUNCTIONS", "LOOPS", "Variables", "IF", "Classes", "Objects", "Casting", "Threads", "Arrays", "Try")
        JAVASCRIPT.toLowerCase() -> listOf("FUNCTIONS", "LOOPS", "Variables", "IF", "Classes", "Objects", "Casting", "Threads", "Arrays", "Try")
        else -> emptyList()
    }

}

