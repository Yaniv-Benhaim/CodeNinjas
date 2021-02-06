package tech.gamedev.codeninjas.ui.battle

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import tech.gamedev.codeninjas.R
import tech.gamedev.codeninjas.databinding.FragmentFinishedBattleBinding


class FinishedBattleFragment : Fragment(R.layout.fragment_finished_battle) {

    private lateinit var binding: FragmentFinishedBattleBinding
    private val args: FinishedBattleFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentFinishedBattleBinding.bind(view)
    }

}