package tech.gamedev.codeninjas.ui.onboarding.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import tech.gamedev.codeninjas.R
import tech.gamedev.codeninjas.databinding.FragmentFirstBinding


class FirstFragment : Fragment() {

  private lateinit var binding: FragmentFirstBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentFirstBinding.bind(view)
        val viewPager = activity?.findViewById<ViewPager2>(R.id.vpOnBoarding)
        binding.btnNext.setOnClickListener {
            viewPager?.currentItem = 1
        }
    }


}