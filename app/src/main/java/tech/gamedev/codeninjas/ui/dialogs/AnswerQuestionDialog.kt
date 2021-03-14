package tech.gamedev.codeninjas.ui.dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.answer_question_dialog.view.*
import kotlinx.android.synthetic.main.cancel_battle_dialog.*
import kotlinx.android.synthetic.main.cancel_battle_dialog.view.*
import kotlinx.android.synthetic.main.cancel_battle_dialog.view.btnCloseDialog
import tech.gamedev.codeninjas.R
import tech.gamedev.codeninjas.utils.setToast
import tech.gamedev.codeninjas.viewmodels.MainViewModel

class AnswerQuestionDialog(val listener: QuestionResultListener): DialogFragment() {

    private val mainViewModel: MainViewModel by activityViewModels()



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        dialog!!.window?.setBackgroundDrawableResource(R.drawable.dialog_bg);
        return inflater.inflate(R.layout.answer_question_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.btnCheckAnswer.setOnClickListener { checkAnswer()}
        view.btnCloseDialog.setOnClickListener { dialog!!.cancel() }
    }

    private fun checkAnswer() {
        val answer = view?.etAnswer?.text
        if (answer.isNullOrEmpty()) {
            Toast.makeText(requireContext(), "Please fill in an answer", Toast.LENGTH_SHORT).show()
        } else {
            if(answer.toString() == "634") {
                listener.getQuestionResult(true)
            } else  {
                listener.getQuestionResult(false)
            }
        }
        dialog!!.cancel()
    }



    override fun onStart() {
        super.onStart()
        val width = (resources.displayMetrics.widthPixels * 0.85).toInt()
        val height = (resources.displayMetrics.heightPixels * 0.30).toInt()
        dialog!!.window?.setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT)
    }



}

interface QuestionResultListener {
    fun getQuestionResult(correct: Boolean)
}