package tech.gamedev.codeninjas.ui.splash

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import tech.gamedev.codeninjas.R
import tech.gamedev.codeninjas.databinding.FragmentSplashBinding


class SplashFragment : Fragment(R.layout.fragment_splash) {
    lateinit var binding: FragmentSplashBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSplashBinding.bind(view)

        binding.btnContinue.setOnClickListener {
            findNavController().navigate(R.id.action_splashFragment_to_chooseWeaponFragment)
        }



    }

}