package tech.gamedev.codeninjas.ui.discuss

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import tech.gamedev.codeninjas.R
import tech.gamedev.codeninjas.adapters.PostAdapter
import tech.gamedev.codeninjas.databinding.FragmentDiscussBinding
import javax.inject.Inject

@AndroidEntryPoint
class DiscussFragment : Fragment(R.layout.fragment_discuss) {

    lateinit var binding: FragmentDiscussBinding
    @Inject
    lateinit var postAdapter: PostAdapter
    private val discussViewModel by viewModels<DiscussViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDiscussBinding.bind(view)
        binding.btnCreateNewPost.setOnClickListener { createNewPost() }
        setupPostRV()
        setupSwipeRefresh()
    }

    private fun setupPostRV() = binding.rvPosts.apply {
        layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        adapter = postAdapter
        postAdapter.startListening()
    }

    private fun setupSwipeRefresh() = binding.swipeRefresh.setOnRefreshListener {
        postAdapter.updateOptions(discussViewModel.getNewQueryOptions())
        binding.swipeRefresh.isRefreshing = false
    }

    private fun createNewPost() {
        findNavController().navigate(R.id.action_discussFragment_to_createNewPostFragment)
    }

    override fun onDestroy() {
        super.onDestroy()
        postAdapter.stopListening()
    }

}