package tech.gamedev.codeninjas.ui.detailscreens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_splash.*
import tech.gamedev.codeninjas.R
import tech.gamedev.codeninjas.adapters.SpecificLessonsAdapter
import tech.gamedev.codeninjas.databinding.FragmentLessonDetailBinding
import tech.gamedev.codeninjas.ui.learn.LearnViewModel
import java.util.*

@AndroidEntryPoint
class LessonDetailFragment : Fragment(R.layout.fragment_lesson_detail), SpecificLessonsAdapter.BtnContinueClickedListener {

    private val args: LessonDetailFragmentArgs by navArgs()
    private val learnViewModel by activityViewModels<LearnViewModel>()
    private lateinit var binding: FragmentLessonDetailBinding
    private lateinit var specificLessonsAdapter: SpecificLessonsAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentLessonDetailBinding.bind(view)
        setupViewPager()
        setupIndicator()
    }

    private fun setupViewPager() = binding.vpLessons.apply {

       specificLessonsAdapter = SpecificLessonsAdapter(
           learnViewModel
               .provideSpecificLessonOptions(args.lesson.collection_link)
               .setLifecycleOwner(this@LessonDetailFragment)
               .build())

       specificLessonsAdapter.listener = this@LessonDetailFragment
       adapter = specificLessonsAdapter
   }

    private fun setupIndicator() {
        TabLayoutMediator(binding.indicator, binding.vpLessons) { tab, position ->
            if (position % 2 == 0) {
                tab.icon = ContextCompat.getDrawable(requireContext(), R.drawable.play)
            } else {
                tab.icon = ContextCompat.getDrawable(requireContext(), R.drawable.question)
            }
        }.attach()
    }

    override fun onLessonClicked() {

    }




}