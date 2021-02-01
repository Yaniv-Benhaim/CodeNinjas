package tech.gamedev.codeninjas.ui.detailscreens

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.RequestManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_splash.*
import tech.gamedev.codeninjas.R
import tech.gamedev.codeninjas.databinding.FragmentLessonDetailBinding
import javax.inject.Inject

@AndroidEntryPoint
class LessonDetailFragment : Fragment(R.layout.fragment_lesson_detail) {

    private val args: LessonDetailFragmentArgs by navArgs()
    @Inject
    lateinit var glide: RequestManager
    private lateinit var binding: FragmentLessonDetailBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentLessonDetailBinding.bind(view)
        binding.tvLessonDetailTitle.text = args.lesson.lessonTitle
        val contentText = args.lesson.lessonContent.replace("_n", "\n")
        binding.tvLessonContent.text = contentText
        if(args.lesson.lessonHasImg) {
            binding.ivLessonCodeExample.isVisible = true
            glide.load(args.lesson.imgUrlOne).into(binding.ivLessonCodeExample)
        }

        binding.btnContinueToQuestion.setOnClickListener { goToQuestion() }


    }

    private fun goToQuestion() {
        val action = LessonDetailFragmentDirections.actionLessonDetailFragmentToQuestionDetailFragment(args.lesson)
        findNavController().navigate(action)
    }


}