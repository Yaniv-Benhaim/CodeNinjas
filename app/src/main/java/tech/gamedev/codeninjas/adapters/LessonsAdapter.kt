package tech.gamedev.codeninjas.adapters

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.firestore.paging.FirestorePagingAdapter
import com.firebase.ui.firestore.paging.FirestorePagingOptions
import com.google.firebase.firestore.DocumentSnapshot
import kotlinx.android.synthetic.main.item_lesson.view.*
import tech.gamedev.codeninjas.R
import tech.gamedev.codeninjas.data.models.lessons.LessonCollectionLink

class LessonsAdapter(options: FirestorePagingOptions<LessonCollectionLink>) :
    FirestorePagingAdapter<LessonCollectionLink, LessonsAdapter.LessonViewHolder>(options)
{
    var lessonsFinished = 0
    var listener: LessonClickedListener? = null

    inner class LessonViewHolder(itemView: View)  : RecyclerView.ViewHolder(itemView) {
        fun initialize() {
            itemView.setOnClickListener {
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    listener?.onLessonClicked(getItem(adapterPosition)!!)
                }
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LessonViewHolder {
        return LessonViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_lesson, parent, false))
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(
        holder: LessonViewHolder,
        position: Int,
        model: LessonCollectionLink
    ) {
        holder.initialize()
        holder.itemView.apply {
            tvLessonId.text =  model.lesson_id
            tvLessonTitle.text = model.module_title
            Log.d("RV", model.lesson_id)
            ivCheckMark.isVisible = lessonsFinished >= position
        }
    }

    fun setProgress(progress: Int) {
        lessonsFinished = progress
    }

    interface LessonClickedListener {
        fun onLessonClicked(documentSnapshot: DocumentSnapshot)
    }

    fun setOnLessonClickedListener(listener: LessonClickedListener) {
        this.listener = listener
    }
}