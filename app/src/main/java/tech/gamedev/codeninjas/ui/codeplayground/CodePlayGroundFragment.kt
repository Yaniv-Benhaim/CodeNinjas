package tech.gamedev.codeninjas.ui.codeplayground

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebSettings.FORCE_DARK_ON
import android.webkit.WebViewClient
import androidx.annotation.RequiresApi
import kotlinx.android.synthetic.main.fragment_code_play_ground.*
import tech.gamedev.codeninjas.R
import tech.gamedev.codeninjas.databinding.FragmentCodePlayGroundBinding
import tech.gamedev.codeninjas.other.Constants.KOTLIN_COMPILER_URL


class CodePlayGroundFragment : Fragment(R.layout.fragment_code_play_ground) {
    lateinit var binding: FragmentCodePlayGroundBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentCodePlayGroundBinding.bind(view)
        setupWebView()
    }


    @SuppressLint("SetJavaScriptEnabled")
    private fun setupWebView() {
        binding.wvCodingArea.webViewClient = WebViewClient()
        binding.wvCodingArea.apply {
            loadUrl(KOTLIN_COMPILER_URL)
            settings.javaScriptEnabled = true
            settings.forceDark = FORCE_DARK_ON


        }
    }

}