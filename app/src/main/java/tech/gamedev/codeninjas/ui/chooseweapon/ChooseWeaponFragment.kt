package tech.gamedev.codeninjas.ui.chooseweapon

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import tech.gamedev.codeninjas.R
import tech.gamedev.codeninjas.adapters.ChooseWeaponAdapter
import tech.gamedev.codeninjas.databinding.FragmentChooseWeaponBinding
import tech.gamedev.codeninjas.ui.learn.LearnViewModel
import tech.gamedev.codeninjas.utils.getWeapons
import tech.gamedev.codeninjas.viewmodels.MainViewModel


class ChooseWeaponFragment : Fragment(R.layout.fragment_choose_weapon), ChooseWeaponAdapter.NextClickedListener, ChooseWeaponAdapter.BackClickedListener {

    lateinit var binding: FragmentChooseWeaponBinding
    lateinit var chooseWeaponAdapter: ChooseWeaponAdapter
    private val _mainViewModel: MainViewModel by activityViewModels()
    private val _learnViewModel: LearnViewModel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentChooseWeaponBinding.bind(view)
        setupChooseWeaponAdapter()
        binding.btnChoose.setOnClickListener { choose() }

    }

    private fun setupChooseWeaponAdapter() = binding.vpChooseYourWeapon.apply {
        chooseWeaponAdapter = ChooseWeaponAdapter()
        chooseWeaponAdapter.setOnBackClickedListener(this@ChooseWeaponFragment)
        chooseWeaponAdapter.setOnNextClickedListener(this@ChooseWeaponFragment)
        adapter = chooseWeaponAdapter

    }

    private fun choose() {
        val curItem = binding.vpChooseYourWeapon.currentItem
        _mainViewModel.setWeapon(getWeapons()[curItem])
        _learnViewModel.setWeapon(getWeapons()[curItem])
        Log.d("WEAPON",getWeapons()[curItem])
        findNavController().navigate(R.id.action_chooseWeaponFragment_to_navigation_home)
    }

    override fun onNextClicked() {
        binding.vpChooseYourWeapon.apply {
            val nextItem = currentItem + 1
            currentItem = nextItem
            Log.d("VP", "NEXT CLICKED")
        }
    }

    override fun onBackClicked() {
        binding.vpChooseYourWeapon.apply {
            val lastItem = currentItem -1
            currentItem = lastItem
            Log.d("VP", "BACK CLICKED")
        }
    }
}