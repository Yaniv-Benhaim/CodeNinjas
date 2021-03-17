package tech.gamedev.codeninjas.ui.activities

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.RequestManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.messaging.FirebaseMessaging
import com.suddenh4x.ratingdialog.AppRating
import com.suddenh4x.ratingdialog.preferences.RatingThreshold
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import tech.gamedev.codeninjas.R
import tech.gamedev.codeninjas.adapters.ChangeWeaponAdapter
import tech.gamedev.codeninjas.databinding.ActivityMainBinding
import tech.gamedev.codeninjas.other.Constants
import tech.gamedev.codeninjas.other.Constants.ADMIN
import tech.gamedev.codeninjas.ui.learn.LearnViewModel
import tech.gamedev.codeninjas.ui.splash.LoginViewModel
import tech.gamedev.codeninjas.viewmodels.MainViewModel
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), ChangeWeaponAdapter.NewWeaponClickedListener {

    private val mainViewModel: MainViewModel by viewModels()
    private val learnViewModel: LearnViewModel by viewModels()
    private val loginViewModel by viewModels<LoginViewModel>()
    lateinit var binding: ActivityMainBinding
    @Inject
    lateinit var glide: RequestManager
    private var _isWeaponMenuOpen = false


    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        AppRating.Builder(this)
                .setMinimumLaunchTimes(5)
                .setMinimumDays(2)
                .setMinimumLaunchTimesToShowAgain(3)
                .setMinimumDaysToShowAgain(3)
                .setRatingThreshold(RatingThreshold.FOUR)
                .setIconDrawable(ContextCompat.getDrawable(this, R.drawable.ic_ninja))
                .showIfMeetsConditions()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        FirebaseMessaging.getInstance().subscribeToTopic(Constants.NOTIFICATION_TOPIC)

        AppRating.Builder(this)
            .setMinimumLaunchTimes(5)
            .setMinimumDays(2)
            .setMinimumLaunchTimesToShowAgain(3)
            .setMinimumDaysToShowAgain(3)
            .setRatingThreshold(RatingThreshold.FOUR)
            .setIconDrawable(ContextCompat.getDrawable(this, R.drawable.ic_ninja))
            .showIfMeetsConditions()


        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
        navView.setupWithNavController(navController)
        setSupportActionBar(toolBar)
        findNavController(R.id.nav_host_fragment).addOnDestinationChangedListener { _, destination, _ ->

            when(destination.id) {
                R.id.homeFragment,
                R.id.learnFragment,
                R.id.discussFragment,
                R.id.battleFragment,
                R.id.codePlayGroundFragment -> {
                    checkIfAdmin()
                    navView.isVisible = true
                    binding.cvToolBar.isVisible = true
                    supportActionBar?.show()
                }
                R.id.interviewQuestionsFragment -> navView.isVisible = false
                else -> {
                    navView.isVisible = false
                    supportActionBar?.hide()
                    binding.cvToolBar.isVisible = false
                }
            }
        }
        //END OF NAVIGATION SETUP
        subscribeToObservers()
        setupChooseNewWeaponRV()
        binding.tvCurrentWeapon.setOnClickListener { openWeaponSelection() }
        binding.ivBtnCode.setOnClickListener { nav_host_fragment.findNavController().navigate(R.id.action_global_to_code_playground)}




    }

    private fun subscribeToObservers() {
        mainViewModel.weapon.observe(this) {
            binding.tvCurrentWeapon.text = it
        }
        mainViewModel.javaQuestions.observe(this) {
            for (question in it) {
                Log.d("BATTLE", question.question)
            }
        }

    }

    private fun openWeaponSelection() {
        binding.cvWeaponSelectionMenu.isVisible = !_isWeaponMenuOpen
        _isWeaponMenuOpen = !_isWeaponMenuOpen
    }

    private fun checkIfAdmin() {
        if(loginViewModel.getCurrentUser() == ADMIN) {
            binding.btnAdmin.isVisible = true
            binding.btnAdmin.setOnClickListener { nav_host_fragment.findNavController().navigate(R.id.action_homeFragment_to_adminFragment) }
        } else {
            binding.btnAdmin.isVisible = false
        }
    }

    private fun setupChooseNewWeaponRV() = binding.rvChangeWeapon.apply {
        val chooseNewWeaponAdapter = ChangeWeaponAdapter(glide)
        chooseNewWeaponAdapter.setOnWeaponClickedListener(this@MainActivity)
        layoutManager = LinearLayoutManager(
            this@MainActivity,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        adapter = chooseNewWeaponAdapter
    }

    override fun onWeaponClicked(newWeapon: String) {
        mainViewModel.setWeapon(newWeapon)
        learnViewModel.setWeapon(newWeapon)
        binding.cvWeaponSelectionMenu.visibility = View.GONE
        _isWeaponMenuOpen = false
    }
}