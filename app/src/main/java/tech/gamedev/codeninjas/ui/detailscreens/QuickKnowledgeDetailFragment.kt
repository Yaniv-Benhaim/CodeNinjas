package tech.gamedev.codeninjas.ui.detailscreens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import kotlinx.android.synthetic.main.fragment_quick_knowledge_detail.*
import kotlinx.android.synthetic.main.item_lesson_only.view.*
import tech.gamedev.codeninjas.R
import tech.gamedev.codeninjas.ui.home.HomeViewModel


class QuickKnowledgeDetailFragment : Fragment(R.layout.fragment_quick_knowledge_detail) {

    private val homeViewModel by activityViewModels<HomeViewModel>()
    private val args by navArgs<QuickKnowledgeDetailFragmentArgs>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        subscribeToObservers()

        youtube_player_view.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                super.onReady(youTubePlayer)
                youTubePlayer.loadVideo(args.videoUrl, 0f)

            }
        })
    }

    private fun subscribeToObservers() {

    }

}