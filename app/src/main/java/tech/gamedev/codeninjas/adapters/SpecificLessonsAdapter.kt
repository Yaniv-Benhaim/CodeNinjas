package tech.gamedev.codeninjas.adapters

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.firebase.ui.firestore.paging.FirestorePagingAdapter
import com.firebase.ui.firestore.paging.FirestorePagingOptions
import com.google.firebase.firestore.DocumentSnapshot
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import kotlinx.android.synthetic.main.item_lesson.view.*
import kotlinx.android.synthetic.main.item_lesson_detail.view.*
import tech.gamedev.codeninjas.R
import tech.gamedev.codeninjas.data.models.LessonAndQuestion
import tech.gamedev.codeninjas.data.models.LessonCollectionLink

class SpecificLessonsAdapter(options: FirestorePagingOptions<LessonAndQuestion>, private val lifeCycle: Lifecycle, private val glide: RequestManager) :
    FirestorePagingAdapter<LessonAndQuestion, SpecificLessonsAdapter.LessonViewHolder>(options)
{

    var listener: BtnContinueClickedListener? = null
    var nextBtnClicked = false

    inner class LessonViewHolder(itemView: View)  : RecyclerView.ViewHolder(itemView) {
        fun initialize() {
            itemView.btnContinueToQuestion.setOnClickListener {

                if(nextBtnClicked && adapterPosition != RecyclerView.NO_POSITION) {
                    nextBtnClicked = false
                    itemView.clQuestionLayout.isVisible = false
                    listener?.onLessonClicked()
                } else  {
                    nextBtnClicked = true
                    itemView.clQuestionLayout.isVisible = true
                    itemView.btnAnswerContainer.isVisible = true
                }

            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LessonViewHolder {
        return LessonViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_lesson_detail, parent, false))
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(
        holder: LessonViewHolder,
        position: Int,
        model: LessonAndQuestion
    ) {
        holder.initialize()
        holder.itemView.apply {
            clQuestionLayout.isVisible = nextBtnClicked
            //SETTING VIDEO
            if(model.hasVideo){
                youtube_player_view.isVisible = true
                youtube_player_view.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
                    override fun onReady(youTubePlayer: YouTubePlayer) {
                        super.onReady(youTubePlayer)
                        youTubePlayer.loadVideo(model.videoUrl, 0f)

                    }
                })

            } else {
                youtube_player_view.isVisible = false
            }
            //END OF VIDEO SETUP

            //SETTING LESSON TITLE
            tvLessonDetailTitle.text = model.lessonTitle
            //SETTING LESSON CONTENT
            tvLessonContent.text = model.lessonContent.replace("_n", "\n")
            //SET LESSON CODE EXAMPLE
            if (model.lessonHasImg){
                ivLessonCodeExample.isVisible = true
                glide.load(model.imgUrlOne).into(ivLessonCodeExample)
            } else  {
                ivLessonCodeExample.isVisible = false
            }

            //SET SECOND AREA OF LESSON CONTENT
            tvLessonContent2.isVisible = false

            //SET SUMMARY
            tvSummary.text = model.summary.replace("_n", "\n")
            //SET QUESTION LAYOUT
            if(clQuestionLayout.isVisible) {
                tvQuestion.text = model.questionOne
                cvQuestionContent.isVisible = false
                if(model.questionOneIsMultipleChoice){
                    etAnswerContainer.isVisible = false
                    btnAnswerContainer.isVisible = true
                    tvBtnAnswerA.text = model.answerA
                    tvBtnAnswerB.text = model.answerB
                    tvBtnAnswerC.text = model.answerC
                    tvBtnAnswerD.text = model.answerD
                } else {
                    etAnswerContainer.isVisible = true
                    btnAnswerContainer.isVisible = false
                }
            }

        }
    }



    interface BtnContinueClickedListener {
        fun onLessonClicked()
    }


}