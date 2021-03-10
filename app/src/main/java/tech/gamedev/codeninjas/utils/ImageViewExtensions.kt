package tech.gamedev.codeninjas.utils

import android.widget.ImageView
import com.bumptech.glide.Glide

fun ImageView.loadImageUrl(url: String) {
    Glide.with(this).load(url).into(this)
}

