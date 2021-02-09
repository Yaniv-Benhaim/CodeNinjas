package tech.gamedev.codeninjas.ui.learn

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import com.firebase.ui.firestore.paging.FirestorePagingOptions
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_learn.*
import tech.gamedev.codeninjas.R
import tech.gamedev.codeninjas.adapters.LessonsAdapter
import tech.gamedev.codeninjas.data.models.LessonCollectionLink
import tech.gamedev.codeninjas.databinding.FragmentLearnBinding


@AndroidEntryPoint
class LearnFragment : Fragment(R.layout.fragment_learn), LessonsAdapter.LessonClickedListener {

    private val _learnViewModel: LearnViewModel by activityViewModels()
    private lateinit var lessonsAdapter: LessonsAdapter
    private lateinit var subject: String
    private lateinit var db: FirebaseFirestore
    private lateinit var binding: FragmentLearnBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentLearnBinding.bind(view)
        subject = _learnViewModel.subject.value!!.toLowerCase()
        db = FirebaseFirestore.getInstance()
        subscribeToObservers()
        setupLessonsRv()






    }

    private fun subscribeToObservers() {
        _learnViewModel.subject.observe(viewLifecycleOwner) {
            subject = it.toLowerCase()
            Log.d("WEAPON", subject)

            val query = db.collection("lessons").document(subject).collection("lessons")
            val config = PagedList.Config.Builder()
                .setInitialLoadSizeHint(30)
                .setPageSize(4)
                .build()

            val options = FirestorePagingOptions.Builder<LessonCollectionLink>()
                .setQuery(query, config, LessonCollectionLink::class.java)
                .setLifecycleOwner(this@LearnFragment).build()
            lessonsAdapter.updateOptions(options)

        }

        _learnViewModel.currentProgressInSteps.observe(viewLifecycleOwner) {
            binding.progressBarCurrentLevel.setProgressWithAnimation(it.toFloat())
        }

        _learnViewModel.userProgress.observe(viewLifecycleOwner) {
            lessonsAdapter.setProgress(it)
        }
    }


    private fun setupLessonsRv() = rvLessons.apply {
        val query = db.collection("lessons").document("java").collection("lessons")
        val config = PagedList.Config.Builder()
            .setInitialLoadSizeHint(30)
            .setPageSize(4)
            .build()

        val options = FirestorePagingOptions.Builder<LessonCollectionLink>()
            .setQuery(query, config, LessonCollectionLink::class.java)
            .setLifecycleOwner(this@LearnFragment).build()

        lessonsAdapter = LessonsAdapter(options)
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