package tech.gamedev.codeninjas.utils

import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.paging.PagedList
import com.firebase.ui.firestore.paging.FirestorePagingOptions
import com.google.firebase.firestore.FirebaseFirestore
import tech.gamedev.codeninjas.data.models.LessonAndQuestion
import tech.gamedev.codeninjas.other.Constants.CPLUSPLUS
import tech.gamedev.codeninjas.other.Constants.JAVA
import tech.gamedev.codeninjas.other.Constants.JAVASCRIPT
import tech.gamedev.codeninjas.other.Constants.KOTLIN
import tech.gamedev.codeninjas.other.Constants.SWIFT
import tech.gamedev.codeninjas.ui.learn.LearnFragment



fun Fragment.setToast(message: String) {
    Toast.makeText(this.requireContext(), message, Toast.LENGTH_SHORT).show()
}

