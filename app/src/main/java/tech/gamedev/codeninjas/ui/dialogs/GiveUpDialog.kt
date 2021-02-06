package tech.gamedev.codeninjas.ui.dialogs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.cancel_battle_dialog.*
import kotlinx.android.synthetic.main.cancel_battle_dialog.view.*
import tech.gamedev.codeninjas.R
import tech.gamedev.codeninjas.utils.setToast
import tech.gamedev.codeninjas.viewmodels.MainViewModel

class GiveUpDialog: DialogFragment() {

    private val mainViewModel: MainViewModel by activityViewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        dialog!!.window?.setBackgroundDrawableResource(R.drawable.dialog_bg);
        return inflater.inflate(R.layout.cancel_battle_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.btnYes.setOnClickListener { giveUp() }
        view.btnNo.setOnClickListener { dialog!!.cancel() }
        view.btnCloseDialog.setOnClickListener { dialog!!.cancel() }
    }

    private fun giveUp() {
        mainViewModel.giveUpBattle()
        dialog!!.cancel()
        findNavController().navigate(R.id.action_global_to_battleFragment)


    }

    override fun onStart() {
        super.onStart()
        val width = (resources.displayMetrics.widthPixels * 0.85).toInt()
        val height = (resources.displayMetrics.heightPixels * 0.30).toInt()
        dialog!!.window?.setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT)
    }
}