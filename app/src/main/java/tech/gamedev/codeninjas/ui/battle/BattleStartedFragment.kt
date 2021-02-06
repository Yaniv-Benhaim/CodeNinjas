package tech.gamedev.codeninjas.ui.battle

import android.os.Bundle
import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.RequestManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.cancel_battle_dialog.*
import kotlinx.android.synthetic.main.fragment_battle_started.*
import tech.gamedev.codeninjas.R
import tech.gamedev.codeninjas.data.models.BattleQuestion
import tech.gamedev.codeninjas.databinding.FragmentBattleStartedBinding
import tech.gamedev.codeninjas.other.Constants
import tech.gamedev.codeninjas.ui.GiveUpDialog
import tech.gamedev.codeninjas.utils.setToast
import tech.gamedev.codeninjas.viewmodels.MainViewModel
import javax.inject.Inject

@AndroidEntryPoint
class BattleStartedFragment : Fragment(R.layout.fragment_battle_started) {

    lateinit var timer: CountDownTimer
    private val args: BattleStartedFragmentArgs by navArgs()
    private val mainViewModel: MainViewModel by activityViewModels()
    lateinit var binding: FragmentBattleStartedBinding
    lateinit var questions: List<BattleQuestion>
    private var currentAnswer = ""
    private var currentQuestion = 0
    private var currentScore = 0
    @Inject
    lateinit var glide: RequestManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        questions = mainViewModel.getFiveRandomJavaQuestions()

        val onBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                showGiveUpDialog()
            }
        }


        requireActivity().onBackPressedDispatcher.addCallback(this, onBackPressedCallback)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentBattleStartedBinding.bind(view)
        setupQuestion()
        setupCountdownFromThirty()
        binding.btnCheckAnswerAndContinue.setOnClickListener { checkQuestion() }
        setupAnswerListeners()


    }

    private fun setupAnswerListeners() {
        binding.btnAnswerA.setOnClickListener {
            currentAnswer = tvBtnAnswerA.text.toString()
            changeSelectedButtonBG(it)
        }

        binding.btnAnswerB.setOnClickListener {
            currentAnswer = tvBtnAnswerB.text.toString()
            changeSelectedButtonBG(it)
        }

        binding.btnAnswerC.setOnClickListener {
            currentAnswer = tvBtnAnswerC.text.toString()
            changeSelectedButtonBG(it)
        }

        binding.btnAnswerD.setOnClickListener {
            currentAnswer = tvBtnAnswerD.text.toString()
            changeSelectedButtonBG(it)
        }
    }

    private fun setupQuestion() {
        val question = questions[currentQuestion]
        binding.tvQuestion.text = question.question
        glide.load(question.questionImgUrl).into(binding.ivQuestionImg)
        binding.tvBtnAnswerA.text = question.answerA
        binding.tvBtnAnswerB.text = question.answerB
        binding.tvBtnAnswerC.text = question.answerC
        binding.tvBtnAnswerD.text = question.answerD

    }

    private fun showGiveUpDialog() {
        GiveUpDialog().show(requireActivity().supportFragmentManager, "Give up Dialog")

    }

    private fun checkQuestion() {
        if (currentAnswer == questions[currentQuestion].correctAnswer) {
            if(currentQuestion < 4) {
                resetSelectedQuestionBG()
                currentQuestion++
                currentScore++
                setupQuestion()
                timer.cancel()
                setupCountdownFromThirty()

            } else {
                currentScore++
                timer.cancel()
                val action = BattleStartedFragmentDirections.actionBattleStartedFragmentToFinishedBattleFragment(currentScore,args.user,args.opponent)
                findNavController().navigate(action)

            }
        } else {
            if(currentQuestion < 4) {
                resetSelectedQuestionBG()
                currentQuestion++
                setupQuestion()
                timer.cancel()
                setupCountdownFromThirty()

            } else {
                timer.cancel()
                val action = BattleStartedFragmentDirections.actionBattleStartedFragmentToFinishedBattleFragment(currentScore,args.user,args.opponent)
                findNavController().navigate(action)

            }
        }
    }

    private fun changeSelectedButtonBG(button: View) {
        when(button.id) {
            R.id.btnAnswerA -> {
                binding.btnAnswerA.setBackgroundResource(R.drawable.btn_answer_selected_bg)
                binding.btnAnswerB.setBackgroundResource(R.drawable.btn_answer_bg)
                binding.btnAnswerC.setBackgroundResource(R.drawable.btn_answer_bg)
                binding.btnAnswerD.setBackgroundResource(R.drawable.btn_answer_bg)
            }
            R.id.btnAnswerB -> {
                binding.btnAnswerB.setBackgroundResource(R.drawable.btn_answer_selected_bg)
                binding.btnAnswerA.setBackgroundResource(R.drawable.btn_answer_bg)
                binding.btnAnswerC.setBackgroundResource(R.drawable.btn_answer_bg)
                binding.btnAnswerD.setBackgroundResource(R.drawable.btn_answer_bg)
            }
            R.id.btnAnswerC -> {
                binding.btnAnswerC.setBackgroundResource(R.drawable.btn_answer_selected_bg)
                binding.btnAnswerB.setBackgroundResource(R.drawable.btn_answer_bg)
                binding.btnAnswerA.setBackgroundResource(R.drawable.btn_answer_bg)
                binding.btnAnswerD.setBackgroundResource(R.drawable.btn_answer_bg)
            }
            R.id.btnAnswerD -> {
                binding.btnAnswerD.setBackgroundResource(R.drawable.btn_answer_selected_bg)
                binding.btnAnswerB.setBackgroundResource(R.drawable.btn_answer_bg)
                binding.btnAnswerC.setBackgroundResource(R.drawable.btn_answer_bg)
                binding.btnAnswerA.setBackgroundResource(R.drawable.btn_answer_bg)
            }
        }
    }

    private fun resetSelectedQuestionBG() {
        binding.btnAnswerA.setBackgroundResource(R.drawable.btn_answer_bg)
        binding.btnAnswerB.setBackgroundResource(R.drawable.btn_answer_bg)
        binding.btnAnswerC.setBackgroundResource(R.drawable.btn_answer_bg)
        binding.btnAnswerD.setBackgroundResource(R.drawable.btn_answer_bg)
    }

    private fun setupCountdownFromThirty() {
        timer = object: CountDownTimer(30000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                   binding.countDownTimer.currentProgress = (30 - (millisUntilFinished / 1000)).toInt()

            }

            override fun onFinish() {
                checkQuestion()
            }
        }
        timer.start()
    }



}