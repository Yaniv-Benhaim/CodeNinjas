package tech.gamedev.codeninjas.ui.detailscreens

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.paging.PagedList
import com.bumptech.glide.RequestManager
import com.firebase.ui.firestore.paging.FirestorePagingOptions
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_splash.*
import tech.gamedev.codeninjas.R
import tech.gamedev.codeninjas.adapters.SpecificLessonsAdapter
import tech.gamedev.codeninjas.data.models.LessonAndQuestion
import tech.gamedev.codeninjas.data.models.LessonCollectionLink
import tech.gamedev.codeninjas.databinding.FragmentLessonDetailBinding
import tech.gamedev.codeninjas.utils.setToast
import tech.gamedev.codeninjas.viewmodels.MainViewModel
import javax.inject.Inject

@AndroidEntryPoint
class LessonDetailFragment : Fragment(R.layout.fragment_lesson_detail), SpecificLessonsAdapter.BtnContinueClickedListener {

    private val args: LessonDetailFragmentArgs by navArgs()
    @Inject
    lateinit var glide: RequestManager
    private val mainViewModel: MainViewModel by activityViewModels()
    private lateinit var binding: FragmentLessonDetailBinding
    private lateinit var specificLessonsAdapter: SpecificLessonsAdapter
    private lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        db = FirebaseFirestore.getInstance()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentLessonDetailBinding.bind(view)
        setupViewPager()



    }

   private fun setupViewPager() = binding.vpLessons.apply {
       val query = db
               .collection("lessons")
               .document(mainViewModel.weapon.value!!.toLowerCase())
               .collection("modules")
               .document(args.lesson.collection_link)
               .collection("lessons")
               .orderBy("lessonId")

       val config = PagedList.Config.Builder()
               .setInitialLoadSizeHint(30)
               .setPageSize(4)
               .build()

       val options = FirestorePagingOptions.Builder<LessonAndQuestion>()
               .setQuery(query, config, LessonAndQuestion::class.java)
               .setLifecycleOwner(this@LessonDetailFragment).build()

       specificLessonsAdapter = SpecificLessonsAdapter(options, glide)
       specificLessonsAdapter.listener = this@LessonDetailFragment
       adapter = specificLessonsAdapter

   }

    override fun onLessonClicked() {
        setToast("CLICK WORKING")
    }


}