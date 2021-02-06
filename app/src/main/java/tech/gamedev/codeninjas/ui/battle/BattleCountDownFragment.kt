package tech.gamedev.codeninjas.ui.battle

import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.RequestManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_battle_count_down.*
import tech.gamedev.codeninjas.R
import tech.gamedev.codeninjas.databinding.FragmentBattleBinding
import tech.gamedev.codeninjas.databinding.FragmentBattleCountDownBinding
import tech.gamedev.codeninjas.other.Constants.FIGHT
import javax.inject.Inject

@AndroidEntryPoint
class BattleCountDownFragment : Fragment(R.layout.fragment_battle_count_down) {

    lateinit var binding: FragmentBattleCountDownBinding
    private val args: BattleCountDownFragmentArgs by navArgs()
    @Inject
    lateinit var glide: RequestManager
    lateinit var timer: CountDownTimer

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentBattleCountDownBinding.bind(view)

        setupOpponentsViews()
        setupCountdownTimer()
    }

    private fun setupOpponentsViews() {
        glide.load(args.user1.profileImg).into(binding.ivProfileImageUser)
        glide.load(args.opponent.profileImg).into(binding.ivProfileImageOpponent)
    }

    private fun setupCountdownTimer() {
        timer = object: CountDownTimer(4000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                if(millisUntilFinished / 1000 != 0L){
                    binding.tvCountDown.text = (millisUntilFinished / 1000).toString()
                } else {
                    binding.tvCountDown.text = FIGHT
                }
            }

            override fun onFinish() {
                val action = BattleCountDownFragmentDirections.actionBattleCountDownFragmentToBattleStartedFragment(args.user1,args.opponent)
                findNavController().navigate(action)
            }
        }
        timer.start()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        timer.cancel()
    }

}