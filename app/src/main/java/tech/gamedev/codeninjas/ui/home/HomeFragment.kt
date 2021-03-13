package tech.gamedev.codeninjas.ui.home

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RemoteViews
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavArgs
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.RequestManager
import com.google.android.material.tabs.TabLayoutMediator
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
import tech.gamedev.codeninjas.databinding.FragmentHomeBinding
import tech.gamedev.codeninjas.other.Constants
import tech.gamedev.codeninjas.other.Constants.INTERVIEW_QUESTIONS
import tech.gamedev.codeninjas.ui.battle.BattleCountDownFragmentDirections
import tech.gamedev.codeninjas.utils.getQuickKnowledge
import tech.gamedev.codeninjas.utils.setToast
import tech.gamedev.codeninjas.viewmodels.MainViewModel
import java.util.*
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home), FeaturedItemsAdapter.ItemClickedListener {


    private val _mainViewModel: MainViewModel by activityViewModels()
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

        btnGoToInterViewQuestions.setOnClickListener { navigateToQueAndAns() }
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

    }


}