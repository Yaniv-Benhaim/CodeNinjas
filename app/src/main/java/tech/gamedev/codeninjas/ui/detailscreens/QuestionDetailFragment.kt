package tech.gamedev.codeninjas.ui.detailscreens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.RequestManager
import tech.gamedev.codeninjas.R
import tech.gamedev.codeninjas.databinding.FragmentQuestionDetailBinding
import tech.gamedev.codeninjas.utils.setToast
import javax.inject.Inject


class QuestionDetailFragment : Fragment(R.layout.fragment_question_detail) {

    private val args: QuestionDetailFragmentArgs by navArgs()
    @Inject
    lateinit var glide: RequestManager
    private lateinit var binding: FragmentQuestionDetailBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentQuestionDetailBinding.bind(view)
        binding.tvLessonQuestion.text = args.question.questionOne
        binding.tvLessonDetailTitle.text = args.question.lessonTitle
        binding.tvBtnAnswerA.text = args.question.answerA
        binding.tvBtnAnswerB.text = args.question.answerB
        binding.tvBtnAnswerC.text = args.question.answerC
        binding.tvBtnAnswerD.text = args.question.answerD

        binding.btnAnswerA.setOnClickListener {checkAnswer(args.question.answerA)}
        binding.btnAnswerB.setOnClickListener {checkAnswer(args.question.answerB)}
        binding.btnAnswerC.setOnClickListener {checkAnswer(args.question.answerC)}
        binding.btnAnswerD.setOnClickListener {checkAnswer(args.question.answerD)}
    }

    private fun checkAnswer(answer: String) {
        if (answer == args.question.questionOneCorrectAnswer) {
            setToast("Congratulations")
        }
    }

}