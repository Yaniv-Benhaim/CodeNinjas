package tech.gamedev.codeninjas.ui.onboarding

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import tech.gamedev.codeninjas.R
import tech.gamedev.codeninjas.adapters.OnBoardingAdapter
import tech.gamedev.codeninjas.databinding.FragmentOnBoardingBinding
import tech.gamedev.codeninjas.ui.onboarding.screens.FirstFragment
import tech.gamedev.codeninjas.ui.onboarding.screens.SecondFragment
import tech.gamedev.codeninjas.ui.onboarding.screens.ThirdFragment


class OnBoardingFragment : Fragment(R.layout.fragment_on_boarding) {
    lateinit var binding: FragmentOnBoardingBinding
    lateinit var fragmentList: ArrayList<Fragment>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentOnBoardingBinding.bind(view)
        setupOnBoardingAdapter()


    }

    private fun setupOnBoardingAdapter() = binding.vpOnBoarding.apply {

        if(onBoardingFinished()){
            findNavController().navigate(R.id.action_global_to_loginFragment)
        }

        fragmentList = arrayListOf(
            FirstFragment(),
            SecondFragment(),
            ThirdFragment()
        )
        adapter = OnBoardingAdapter(
            fragmentList,
            requireActivity().supportFragmentManager,
            lifecycle)
    }

    private fun onBoardingFinished(): Boolean {
        val sharedPref = requireActivity().getSharedPreferences("onBoarding", Context.MODE_PRIVATE)
        return sharedPref.getBoolean("Finished", false)
    }



}