package tech.gamedev.codeninjas.ui.detailscreens

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.ParcelFileDescriptor.open
import android.view.View
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import tech.gamedev.codeninjas.R
import tech.gamedev.codeninjas.databinding.FragmentInterviewQuestionsBinding
import tech.gamedev.codeninjas.other.Constants
import tech.gamedev.codeninjas.other.Constants.CPLUSPLUS
import tech.gamedev.codeninjas.other.Constants.CPLUSPLUS_INTERVIEW_QUESTIONS_URL
import tech.gamedev.codeninjas.other.Constants.JAVA
import tech.gamedev.codeninjas.other.Constants.JAVASCRIPT
import tech.gamedev.codeninjas.other.Constants.JAVASCRIPT_INTERVIEW_QUESTIONS_URL
import tech.gamedev.codeninjas.other.Constants.JAVA_INTERVIEW_QUESTIONS_URL
import tech.gamedev.codeninjas.other.Constants.KOTLIN
import tech.gamedev.codeninjas.other.Constants.KOTLIN_INTERVIEW_QUESTIONS_URL
import tech.gamedev.codeninjas.other.Constants.SWIFT
import tech.gamedev.codeninjas.other.Constants.SWIFT_INTERVIEW_QUESTIONS_URL
import tech.gamedev.codeninjas.viewmodels.MainViewModel
import java.io.InputStream
import java.nio.channels.AsynchronousFileChannel.open


class InterviewQuestionsFragment : Fragment(R.layout.fragment_interview_questions) {

  private val mainViewModel: MainViewModel by activityViewModels()
    lateinit var binding: FragmentInterviewQuestionsBinding
    val args: InterviewQuestionsFragmentArgs by navArgs()
    private var localUrl: String = ""

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentInterviewQuestionsBinding.bind(view)
        subscribeToObservers()
        localUrl = args.weapon
        setupWebView()



    }

    private fun subscribeToObservers() {
        mainViewModel.weapon.observe(viewLifecycleOwner) {
            when(it) {
                KOTLIN -> localUrl = KOTLIN_INTERVIEW_QUESTIONS_URL
                JAVA -> localUrl = JAVA_INTERVIEW_QUESTIONS_URL
                CPLUSPLUS -> localUrl = CPLUSPLUS_INTERVIEW_QUESTIONS_URL
                SWIFT -> localUrl = SWIFT_INTERVIEW_QUESTIONS_URL
                JAVASCRIPT -> localUrl = JAVASCRIPT_INTERVIEW_QUESTIONS_URL
            }
            setupWebView()
        }
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun setupWebView() {
        binding.webViewInterViewQuestions.webViewClient = WebViewClient()
        binding.webViewInterViewQuestions.apply {
            settings.javaScriptEnabled = true
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.Q) {
                settings.forceDark = WebSettings.FORCE_DARK_ON
            }
            settings.allowContentAccess = true
            settings.domStorageEnabled = true
            settings.allowFileAccess = true
            settings.allowUniversalAccessFromFileURLs = true
            loadUrl(localUrl)


        }
    }




}