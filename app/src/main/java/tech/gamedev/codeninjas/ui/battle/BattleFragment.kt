package tech.gamedev.codeninjas.ui.battle

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.RequestManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_battle.*
import tech.gamedev.codeninjas.R
import tech.gamedev.codeninjas.adapters.UserBattleAdapter
import tech.gamedev.codeninjas.databinding.FragmentBattleBinding
import tech.gamedev.codeninjas.ui.splash.LoginViewModel
import tech.gamedev.codeninjas.utils.setToast
import tech.gamedev.codeninjas.viewmodels.MainViewModel
import javax.inject.Inject
import kotlin.random.Random

@AndroidEntryPoint
class BattleFragment : Fragment(R.layout.fragment_battle), UserBattleAdapter.OnUserClicked {

    private val mainViewModel: MainViewModel by activityViewModels()
    private val loginViewModel: LoginViewModel by activityViewModels()
    private lateinit var userBattleAdapter: UserBattleAdapter
    private lateinit var binding: FragmentBattleBinding
    @Inject
    lateinit var glide: RequestManager

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentBattleBinding.bind(view)
        if(!mainViewModel.dummyUsersForBattle.value.isNullOrEmpty()){
            userBattleAdapter = UserBattleAdapter(mainViewModel.dummyUsersForBattle.value!!, glide)
        }
        subscribeToObservers()


        binding.btnBattleRandomOpponent.setOnClickListener { battleRandomOpponent(it) }

    }

    private fun battleRandomOpponent(view: View) {
        val position: Int = Random.nextInt(0,mainViewModel.dummyUsersForBattle.value!!.lastIndex)
        val user = mainViewModel.dummyUsersForBattle.value?.get(position)

        view.animate().apply {
            duration = 200
            rotationXBy(360f)
        }.withEndAction {
            mainViewModel.getJavaQuestions()
            val action = BattleFragmentDirections.actionBattleFragmentToBattleCountDownFragment(loginViewModel.user.value!!,user!!)
            findNavController().navigate(action)
        }
    }

    private fun getTopScorers() {
        mainViewModel.getTopScorers()
    }

    private fun subscribeToObservers() {
        mainViewModel.dummyUsersForBattle.observe(viewLifecycleOwner) {
            userBattleAdapter = UserBattleAdapter(it, glide)
            setBattleRv()
            userBattleAdapter.notifyDataSetChanged()
            getTopScorers()
            Log.d("BATTLE", it.size.toString())
        }

        mainViewModel.topScores.observe(viewLifecycleOwner) {
            if (it != null) {
                binding.tvFirstPlaceUser.text = it.last().userName
                binding.tvFirstPlaceUserScore.text = it.last().battlesWon.toString()

                binding.tvSecondPlaceUser.text = it[it.lastIndex -1].userName
                binding.tvSecondPlaceUserScore.text = it[it.lastIndex -1].battlesWon.toString()

                binding.tvThirdPlaceUser.text = it[it.lastIndex -2].userName
                binding.tvThirdPlaceUserScore.text = it[it.lastIndex -2].battlesWon.toString()

                binding.tvFourthPlaceUser.text = it[it.lastIndex -3].userName
                binding.tvFourthPlaceUserScore.text = it[it.lastIndex -3].battlesWon.toString()

                binding.tvFifthPlaceUser.text = it[it.lastIndex -4].userName
                binding.tvFifthPlaceUserScore.text = it[it.lastIndex -4].battlesWon.toString()
            }
        }


    }

    private fun setBattleRv() = binding.rvUsersAvailableForBattle.apply {
        layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL, false)
        userBattleAdapter.listener = this@BattleFragment
        adapter = userBattleAdapter
    }

    override fun userClicked(position: Int) {
        binding.cvChallengeUserContainer.isVisible = !binding.cvChallengeUserContainer.isVisible
        val user = mainViewModel.dummyUsersForBattle.value?.get(position)

        binding.tvBattleUserName.text = user!!.userName
        binding.tvBattleUserLevelValue.text = user.level
        binding.tvBattlesWonValue.text = user.battlesWon.toString()
        binding.tvBattlesLostValue.text = user.battlesLost.toString()
        glide.load(user.profileImg).into(ivBattleUserProfileImg)
        binding.btnChallengeOpponent.setOnClickListener {
            it.animate().apply {
                duration = 200
                rotationXBy(360f)
            }.withEndAction {
                mainViewModel.getJavaQuestions()
               val action = BattleFragmentDirections.actionBattleFragmentToBattleCountDownFragment(loginViewModel.user.value!!,user)
                findNavController().navigate(action)
            }
        }
    }
}