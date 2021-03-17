package tech.gamedev.codeninjas.ui.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.android.material.tabs.TabLayoutMediator
import com.google.firebase.firestore.FirebaseFirestore
import com.suddenh4x.ratingdialog.AppRating
import com.suddenh4x.ratingdialog.preferences.RatingThreshold
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import tech.gamedev.codeninjas.R
import tech.gamedev.codeninjas.adapters.FeaturedItemsAdapter
import tech.gamedev.codeninjas.data.models.categories.QuickKnowledge
import tech.gamedev.codeninjas.databinding.FragmentHomeBinding
import tech.gamedev.codeninjas.other.Constants.INTERVIEW_QUESTIONS
import tech.gamedev.codeninjas.ui.dialogs.AnswerQuestionDialog
import tech.gamedev.codeninjas.ui.dialogs.QuestionResultListener
import tech.gamedev.codeninjas.utils.getQuickKnowledge
import tech.gamedev.codeninjas.viewmodels.MainViewModel
import java.util.*
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home), FeaturedItemsAdapter.ItemClickedListener, QuestionResultListener {


    private val _mainViewModel: MainViewModel by activityViewModels()
    private val homeViewModel by activityViewModels<HomeViewModel>()
    private lateinit var binding: FragmentHomeBinding
    @Inject
    lateinit var featuredAdapter: FeaturedItemsAdapter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)
        getQuestionAndUserData()
        subscribeToObservers()
        setupFeaturedVP()
        setupIndicator()
        binding.btnQuestionOfTheDay.setOnClickListener { showQuestionOfTheDay() }
        btnGoToInterViewQuestions.setOnClickListener { navigateToQueAndAns() }
        /*createCategories()*/
    }

    @SuppressLint("SetTextI18n")
    private fun subscribeToObservers() {
        _mainViewModel.weapon.observe(viewLifecycleOwner) {
            binding.tvBtnGoToInterviewQuestions.text = "$it  $INTERVIEW_QUESTIONS"
            setupCategories(it.toLowerCase(Locale.ROOT))
        }
    }

    private fun navigateToQueAndAns() {
        val action = HomeFragmentDirections.actionGlobalToInterviewQuestionsFragment(_mainViewModel.weapon.value!!)
        findNavController().navigate(action)
    }

    private fun setupFeaturedVP() = binding.vpFeaturedItems.apply {

        featuredAdapter.setOnNextClickedListener(this@HomeFragment)
        adapter = featuredAdapter
        setupCountdownTimer()
    }

    private fun setupIndicator() {
        TabLayoutMediator(binding.indicator, binding.vpFeaturedItems) { tab, position ->

        }.attach()
    }

    private fun getQuestionAndUserData() {
        //GET DUMMY USERS FOR BATTLE SYSTEM
        _mainViewModel.getDummyUsersForBattle()
        _mainViewModel.getJavaQuestions()

    }

    override fun onNextClicked(position: Int) {
        when(position) {
            0 -> findNavController().navigate(R.id.action_global_to_battleFragment)
            1 -> findNavController().navigate(R.id.action_global_to_learnFragment)
            2 -> findNavController().navigate(R.id.action_global_to_discussFragment)
            3 -> {
                AppRating.Builder(requireActivity() as AppCompatActivity)
                        .setMinimumLaunchTimes(5)
                        .setMinimumDays(2)
                        .setMinimumLaunchTimesToShowAgain(3)
                        .setMinimumDaysToShowAgain(3)
                        .setRatingThreshold(RatingThreshold.FOUR)
                        .setIconDrawable(ContextCompat.getDrawable(requireContext(),R.drawable.ic_ninja))
                        .showNow()
            }
        }
    }

    private fun setupCountdownTimer() {
        val timer = object: CountDownTimer(20000, 6000) {
            override fun onTick(millisUntilFinished: Long) {
                val curItem = binding.vpFeaturedItems.currentItem
                when(binding.vpFeaturedItems.currentItem) {
                    0 -> binding.vpFeaturedItems.currentItem = curItem + 1
                    1 -> binding.vpFeaturedItems.currentItem = curItem + 1
                    2 -> binding.vpFeaturedItems.currentItem = curItem + 1
                    3 -> binding.vpFeaturedItems.currentItem = 0
                }
            }

            override fun onFinish() {
                setupCountdownTimer()
            }
        }
        timer.start()
    }

    private fun setupCategories(subject: String) {
        binding.btnCategory1.text = getQuickKnowledge(subject)[0]
        binding.btnCategory2.text = getQuickKnowledge(subject)[1]
        binding.btnCategory3.text = getQuickKnowledge(subject)[2]
        binding.btnCategory4.text = getQuickKnowledge(subject)[3]
        binding.btnCategory5.text = getQuickKnowledge(subject)[4]
        binding.btnCategory6.text = getQuickKnowledge(subject)[5]
        binding.btnCategory7.text = getQuickKnowledge(subject)[6]
        binding.btnCategory8.text = getQuickKnowledge(subject)[7]
        binding.btnCategory9.text = getQuickKnowledge(subject)[8]
        binding.btnCategory10.text = getQuickKnowledge(subject)[9]

        binding.btnCategory1.setOnClickListener {
            homeViewModel.getQuickKnowledgeVideo(_mainViewModel.weapon.value!!.toLowerCase(Locale.ROOT),
                btnCategory1.text.toString().toUpperCase().trim())

            Log.d("QUICK", "language: ${_mainViewModel.weapon.value!!} topic: ${btnCategory1.text.toString().trim().toUpperCase()} ")
            findNavController().navigate(R.id.action_homeFragment_to_quickKnowledgeDetailFragment)
        }
        binding.btnCategory2.setOnClickListener {
            homeViewModel.getQuickKnowledgeVideo(_mainViewModel.weapon.value!!, btnCategory1.text.toString())

        }
        binding.btnCategory3.setOnClickListener {
            homeViewModel.getQuickKnowledgeVideo(_mainViewModel.weapon.value!!, btnCategory1.text.toString())

        }
        binding.btnCategory4.setOnClickListener {
            homeViewModel.getQuickKnowledgeVideo(_mainViewModel.weapon.value!!, btnCategory1.text.toString())

        }
        binding.btnCategory5.setOnClickListener {
            homeViewModel.getQuickKnowledgeVideo(_mainViewModel.weapon.value!!, btnCategory1.text.toString())

        }
        binding.btnCategory6.setOnClickListener {
            homeViewModel.getQuickKnowledgeVideo(_mainViewModel.weapon.value!!, btnCategory1.text.toString())

        }
        binding.btnCategory7.setOnClickListener {
            homeViewModel.getQuickKnowledgeVideo(_mainViewModel.weapon.value!!, btnCategory1.text.toString())

        }
        binding.btnCategory8.setOnClickListener {
            homeViewModel.getQuickKnowledgeVideo(_mainViewModel.weapon.value!!, btnCategory1.text.toString())

        }
        binding.btnCategory9.setOnClickListener {
            homeViewModel.getQuickKnowledgeVideo(_mainViewModel.weapon.value!!, btnCategory1.text.toString())

        }
        binding.btnCategory10.setOnClickListener {
            homeViewModel.getQuickKnowledgeVideo(_mainViewModel.weapon.value!!, btnCategory1.text.toString())

        }

    }

    private fun showQuestionOfTheDay() {
        AnswerQuestionDialog(this).show(requireActivity().supportFragmentManager, "Answer Question Dialog")
    }

    override fun getQuestionResult(correct: Boolean) {
        if (correct) {
            binding.levelUpAnim.isVisible = true
            binding.levelUpAnim.playAnimation()
            lifecycleScope.launch {
                delay(3000)
                withContext(Dispatchers.Main) {
                    binding.levelUpAnim.isVisible = false
                }


            }
        } else {
            Toast.makeText(requireContext(), "Wrong answer!! Please try again later.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun createCategories() {
        val db = FirebaseFirestore.getInstance()
        val collectionRef = db.collection("quick_knowledge").document("java").collection("categories")
        collectionRef.document("FUNCTIONS").set(QuickKnowledge("FUNCTIONS", "cCgOESMQe44"))
        collectionRef.document("LOOPS").set(QuickKnowledge("LOOPS", "6djggrlkHY8"))
        collectionRef.document("VARIABLES").set(QuickKnowledge("VARIABLES", "1mRN2MwdWUo"))
        collectionRef.document("IF").set(QuickKnowledge("IF", "yvWnj_HfG6s"))
        collectionRef.document("CLASSES").set(QuickKnowledge("CLASSES", "vjjjGkXpX_I"))
        collectionRef.document("OBJECTS").set(QuickKnowledge("OBJECTS", "Mm06BuD3PlY"))
        collectionRef.document("CASTING").set(QuickKnowledge("CASTING", "H0LNjF9PSeM"))
        collectionRef.document("THREADS").set(QuickKnowledge("THREADS", "eQk5AWcTS8w"))
        collectionRef.document("ARRAYS").set(QuickKnowledge("ARRAYS", "xzjZy-dHHLw"))
        collectionRef.document("TRY").set(QuickKnowledge("TRY", "ceGnVDrMy1A"))
    }
}