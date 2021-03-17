package tech.gamedev.codeninjas.ui.learn

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.ktx.toObject
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_learn.*
import tech.gamedev.codeninjas.R
import tech.gamedev.codeninjas.adapters.LessonsAdapter
import tech.gamedev.codeninjas.data.models.lessons.LessonCollectionLink
import tech.gamedev.codeninjas.databinding.FragmentLearnBinding


@AndroidEntryPoint
class LearnFragment : Fragment(R.layout.fragment_learn), LessonsAdapter.LessonClickedListener {

    private val _learnViewModel: LearnViewModel by activityViewModels()
    private lateinit var lessonsAdapter: LessonsAdapter
    private lateinit var subject: String
    private lateinit var binding: FragmentLearnBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentLearnBinding.bind(view)
        subject = _learnViewModel.subject.value!!.toLowerCase()
        subscribeToObservers()
        setupLessonsRv()
//        _learnViewModel.createNewLesson()
    }

    private fun subscribeToObservers() {
        _learnViewModel.subject.observe(viewLifecycleOwner) {
            subject = it.toLowerCase()

            lessonsAdapter.updateOptions(
                _learnViewModel
                    .provideUpdatedOptions(subject)
                    .setLifecycleOwner(this)
                    .build())

        }

        _learnViewModel.currentProgressInSteps.observe(viewLifecycleOwner) {
            binding.progressBarCurrentLevel.setProgressWithAnimation(it.toFloat())
        }

        _learnViewModel.userProgress.observe(viewLifecycleOwner) {
            lessonsAdapter.setProgress(it)
        }
    }

    private fun setupLessonsRv() = rvLessons.apply {
        lessonsAdapter = LessonsAdapter(_learnViewModel.provideUpdatedOptions(subject).setLifecycleOwner(this@LearnFragment).build())
        lessonsAdapter.setOnLessonClickedListener(this@LearnFragment)
        layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL, false)
        adapter = lessonsAdapter
    }

    override fun onLessonClicked(documentSnapshot: DocumentSnapshot) {
        val lesson = documentSnapshot.toObject<LessonCollectionLink>()
        val action = LearnFragmentDirections.actionLearnFragmentToLessonDetailFragment(lesson!!)
        findNavController().navigate(action)
    }
}