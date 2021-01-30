package tech.gamedev.codeninjas.utils

import android.widget.Toast
import androidx.fragment.app.Fragment

fun Fragment.setToast(message: String) {
    Toast.makeText(this.requireContext(), message, Toast.LENGTH_SHORT).show()
}