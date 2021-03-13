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
import com.firebase.ui.firestore.paging.FirestorePagingAdapter
import com.firebase.ui.firestore.paging.FirestorePagingOptions
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import kotlinx.android.synthetic.main.item_lesson_only.view.*
import kotlinx.android.synthetic.main.item_lesson_only.view.btnContinueToQuestion
import kotlinx.android.synthetic.main.item_lesson_only.view.ivLessonCodeExample
import kotlinx.android.synthetic.main.item_lesson_only.view.tvLessonContent
import kotlinx.android.synthetic.main.item_lesson_only.view.tvLessonContent2
import kotlinx.android.synthetic.main.item_lesson_only.view.tvLessonDetailTitle
import kotlinx.android.synthetic.main.item_lesson_only.view.tvSummary
import kotlinx.android.synthetic.main.item_lesson_only.view.vYoutubeRatio
import kotlinx.android.synthetic.main.item_lesson_only.view.youtube_player_view
import kotlinx.android.synthetic.main.item_question_only.view.*
import kotlinx.android.synthetic.main.item_question_only.view.btnAnswerContainer
import kotlinx.android.synthetic.main.item_question_only.view.etAnswerContainer
import kotlinx.android.synthetic.main.item_question_only.view.tvBtnAnswerA
import kotlinx.android.synthetic.main.item_question_only.view.tvBtnAnswerB
import kotlinx.android.synthetic.main.item_question_only.view.tvBtnAnswerC
import kotlinx.android.synthetic.main.item_question_only.view.tvBtnAnswerD
import kotlinx.android.synthetic.main.item_question_only.view.tvLessonQuestion
import kotlinx.coroutines.*
import tech.gamedev.codeninjas.R
import tech.gamedev.codeninjas.data.models.LessonAndQuestion
import tech.gamedev.codeninjas.utils.getFirstText
import tech.gamedev.codeninjas.utils.getSecondText
import tech.gamedev.codeninjas.utils.loadImageUrl
import tech.gamedev.codeninjas.utils.textContainsImage


class SpecificLessonsAdapter(options: FirestorePagingOptions<LessonAndQuestion>) :
    FirestorePagingAdapter<LessonAndQuestion, SpecificLessonsAdapter.LessonViewHolder>(options)
{

    var listener: BtnContinueClickedListener? = null
    var nextBtnClicked = false
    var chosenAnswer: String = ""

    inner class LessonViewHolder(itemView: View)  : RecyclerView.ViewHolder(itemView) {
        fun initialize() {
            /*itemView.btnContinueToQuestion.setOnClickListener {

                if(nextBtnClicked && adapterPosition != RecyclerView.NO_POSITION) {
                    nextBtnClicked = false
                    listener?.onLessonClicked()
                } else  {
                    nextBtnClicked = true
                }

            }*/


        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LessonViewHolder {

        return when(viewType){
            0 -> LessonViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_lesson_only, parent, false))
            else -> LessonViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_question_only, parent, false))
        }

    }

    override fun getItemViewType(position: Int): Int {
        return position % 2 * 2
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(
        holder: LessonViewHolder,
        position: Int,
        model: LessonAndQuestion
    ) {
        holder.initialize()
        when(holder.itemViewType) {
            0 -> {
                holder.itemView.apply {
                    if(model.hasVideo){
                        vYoutubeRatio.isVisible = true
                        youtube_player_view.isVisible = true
                        youtube_player_view.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
                            override fun onReady(youTubePlayer: YouTubePlayer) {
                                super.onReady(youTubePlayer)
                                youTubePlayer.loadVideo(model.videoUrl, 0f)

                            }
                        })

                    } else {
                        youtube_player_view.isVisible = false
                        vYoutubeRatio.isVisible = false
                    }

                    if(tvLessonContent.textContainsImage(model)) {
                        tvLessonText2.isVisible = true
                        tvLessonContent.getFirstText(model)
                        tvLessonText2.getSecondText(model)
                        cvCodeExample.isVisible = true
                        ivLessonCodeImage.loadImageUrl(model.imgUrlOne)
                    } else {
                        tvLessonContent.getFirstText(model)
                        tvLessonText2.isVisible = false
                        cvCodeExample.isVisible = false
                    }

                    tvLessonDetailTitle.text = model.lessonTitle

                    //SET SECOND AREA OF LESSON CONTENT
                    tvLessonContent2.isVisible = false

                    //SET SUMMARY
                    tvSummary.text = "Summary\n" + model.summary.replace("_n", "\n")
                    //SET QUESTION LAYOUT


                }
            }
            2 -> {
                //QUESTION
                holder.itemView.apply {
                    lottieCorrectAnim.isVisible = false
                    lottieWrongAnim.isVisible = false
                    tvLessonQuestion.text = model.questionOne
                    if(model.questionOneIsMultipleChoice){
                        etAnswerContainer.isVisible = false
                        btnAnswerContainer.isVisible = true

                        tvBtnAnswerA.text = model.answerA
                        tvBtnAnswerB.text = model.answerB
                        tvBtnAnswerC.text = model.answerC
                        tvBtnAnswerD.text = model.answerD

                        tvBtnAnswerA.setBackgroundResource(R.drawable.btn_answer_bg)
                        tvBtnAnswerB.setBackgroundResource(R.drawable.btn_answer_bg)
                        tvBtnAnswerC.setBackgroundResource(R.drawable.btn_answer_bg)
                        tvBtnAnswerD.setBackgroundResource(R.drawable.btn_answer_bg)

                        tvBtnAnswerA.setOnClickListener {
                            it.setBackgroundResource(R.drawable.btn_answer_selected_bg)
                            tvBtnAnswerB.setBackgroundResource(R.drawable.btn_answer_bg)
                            tvBtnAnswerC.setBackgroundResource(R.drawable.btn_answer_bg)
                            tvBtnAnswerD.setBackgroundResource(R.drawable.btn_answer_bg)
                            chosenAnswer = model.answerA

                        }
                        tvBtnAnswerB.setOnClickListener {
                            it.setBackgroundResource(R.drawable.btn_answer_selected_bg)
                            tvBtnAnswerA.setBackgroundResource(R.drawable.btn_answer_bg)
                            tvBtnAnswerC.setBackgroundResource(R.drawable.btn_answer_bg)
                            tvBtnAnswerD.setBackgroundResource(R.drawable.btn_answer_bg)
                            chosenAnswer = model.answerB
                        }
                        tvBtnAnswerC.setOnClickListener {
                            it.setBackgroundResource(R.drawable.btn_answer_selected_bg)
                            tvBtnAnswerA.setBackgroundResource(R.drawable.btn_answer_bg)
                            tvBtnAnswerB.setBackgroundResource(R.drawable.btn_answer_bg)
                            tvBtnAnswerD.setBackgroundResource(R.drawable.btn_answer_bg)
                            chosenAnswer = model.answerC
                        }
                        tvBtnAnswerD.setOnClickListener {
                            it.setBackgroundResource(R.drawable.btn_answer_selected_bg)
                            tvBtnAnswerB.setBackgroundResource(R.drawable.btn_answer_bg)
                            tvBtnAnswerC.setBackgroundResource(R.drawable.btn_answer_bg)
                            tvBtnAnswerA.setBackgroundResource(R.drawable.btn_answer_bg)
                            chosenAnswer = model.answerD
                        }

                        btnAnswer.setOnClickListener {
                            if (chosenAnswer == model.questionOneCorrectAnswer) {
                                lottieCorrectAnim.isVisible = true
                                lottieCorrectAnim.playAnimation()
                                CoroutineScope(Dispatchers.IO).launch {
                                    delay(2000)
                                    withContext(Dispatchers.Main) {
                                        lottieCorrectAnim.visibility = View.GONE
                                    }
                                }
                            } else {

                                lottieWrongAnim.isVisible = true
                                lottieWrongAnim.playAnimation()
                                CoroutineScope(Dispatchers.IO).launch {
                                    delay(2000)
                                    withContext(Dispatchers.Main) {
                                        lottieWrongAnim.visibility = View.GONE
                                    }
                                }
                            }
                        }
                    } else {
                        etAnswerContainer.isVisible = true
                        btnAnswerContainer.isVisible = false



                        btnAnswer.setOnClickListener {
                            if (etAnswer.text.toString().equals(model.questionOneCorrectAnswer, ignoreCase = true)) {
                                lottieCorrectAnim.isVisible = true
                                lottieCorrectAnim.playAnimation()
                                CoroutineScope(Dispatchers.IO).launch {
                                    delay(2000)
                                    withContext(Dispatchers.Main) {
                                        lottieCorrectAnim.visibility = View.GONE
                                    }
                                }
                            } else {

                                lottieWrongAnim.isVisible = true
                                lottieWrongAnim.playAnimation()
                                CoroutineScope(Dispatchers.IO).launch {
                                    delay(2000)
                                    withContext(Dispatchers.Main) {
                                        lottieWrongAnim.visibility = View.GONE
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }



    interface BtnContinueClickedListener {
        fun onLessonClicked()
    }


}