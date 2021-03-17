package tech.gamedev.codeninjas.ui.detailscreens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import tech.gamedev.codeninjas.R
import tech.gamedev.codeninjas.ui.home.HomeViewModel


class QuickKnowledgeDetailFragment : Fragment(R.layout.fragment_quick_knowledge_detail) {

    private val homeViewModel by activityViewModels<HomeViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        subscribeToObservers()
    }

    private fun subscribeToObservers() {
        homeViewModel.quickKnowledge.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), it.toString(), Toast.LENGTH_SHORT).show()
        }
    }

}