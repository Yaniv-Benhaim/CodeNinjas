package tech.gamedev.codeninjas.ui

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.RequestManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import tech.gamedev.codeninjas.R
import tech.gamedev.codeninjas.adapters.ChangeWeaponAdapter
import tech.gamedev.codeninjas.databinding.ActivityMainBinding
import tech.gamedev.codeninjas.ui.learn.LearnViewModel
import tech.gamedev.codeninjas.viewmodels.MainViewModel
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), ChangeWeaponAdapter.NewWeaponClickedListener {

    private val mainViewModel: MainViewModel by viewModels()
    private val learnViewModel: LearnViewModel by viewModels()
    lateinit var binding: ActivityMainBinding
    @Inject
    lateinit var glide: RequestManager
    private var _isWeaponMenuOpen = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
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
                R.id.codePlayGroundFragment,
                R.id.interviewQuestionsFragment -> {
                    navView.isVisible = true
                    binding.cvToolBar.isVisible = true
                    supportActionBar?.show()
                }
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
        binding.ivBtnCode.setOnClickListener { nav_host_fragment.findNavController().navigate(R.id.action_global_to_code_playground) }


    }

    private fun subscribeToObservers() {
        mainViewModel.weapon.observe(this) {
            binding.tvCurrentWeapon.text = it
        }
    }

    private fun openWeaponSelection() {
        if(_isWeaponMenuOpen) {
            binding.cvWeaponSelectionMenu.visibility = View.GONE
            _isWeaponMenuOpen = false
        } else {
            binding.cvWeaponSelectionMenu.isVisible = true
            _isWeaponMenuOpen = true
        }
    }

    private fun setupChooseNewWeaponRV() = binding.rvChangeWeapon.apply {
        val chooseNewWeaponAdapter = ChangeWeaponAdapter(glide)
        chooseNewWeaponAdapter.setOnWeaponClickedListener(this@MainActivity)
        layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false )
        adapter = chooseNewWeaponAdapter
    }

    override fun onWeaponClicked(newWeapon: String) {
        mainViewModel.setWeapon(newWeapon)
        learnViewModel.setWeapon(newWeapon)
        binding.cvWeaponSelectionMenu.visibility = View.GONE
        _isWeaponMenuOpen = false
    }
}