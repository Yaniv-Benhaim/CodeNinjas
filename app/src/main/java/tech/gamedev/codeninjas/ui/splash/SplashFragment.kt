package tech.gamedev.codeninjas.ui.splash

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.*
import kotlinx.coroutines.tasks.await
import tech.gamedev.codeninjas.R
import tech.gamedev.codeninjas.databinding.FragmentSplashBinding
import tech.gamedev.codeninjas.other.Constants.AUTH_REQUEST_CODE
import tech.gamedev.codeninjas.other.Constants.LOGIN_EVENT
import tech.gamedev.codeninjas.utils.setToast


class SplashFragment : Fragment(R.layout.fragment_splash) {
    private lateinit var auth: FirebaseAuth
    private lateinit var firestore: FirebaseFirestore
    private val loginViewModel: LoginViewModel by activityViewModels()
    private lateinit var firebaseAnalytics: FirebaseAnalytics
    lateinit var binding: FragmentSplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        firebaseAnalytics = FirebaseAnalytics.getInstance(requireContext())
        auth = FirebaseAuth.getInstance()
        firestore = FirebaseFirestore.getInstance()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSplashBinding.bind(view)

        binding.btnContinue.setOnClickListener { signIn() }

    }

    private fun signIn() {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        val signInClient = GoogleSignIn.getClient(requireContext(), gso)

        signInClient.signInIntent.also {
            startActivityForResult(it, AUTH_REQUEST_CODE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == AUTH_REQUEST_CODE) {
            try {
                val account = GoogleSignIn.getSignedInAccountFromIntent(data).result
                account?.let {
                    googleAuthForFirebase(it)
                }
            } catch (e: Exception) {
                Log.d("LOGIN", e.message.toString())
            }
        }
    }

    private fun googleAuthForFirebase(account: GoogleSignInAccount) {
        val credentials = GoogleAuthProvider.getCredential(account.idToken, null)
        CoroutineScope(Dispatchers.IO).launch {
            try {
                auth.signInWithCredential(credentials).await()
                val bundle = Bundle()
                bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, LOGIN_EVENT)
                firebaseAnalytics.logEvent(FirebaseAnalytics.Event.LOGIN, bundle)
                loginViewModel.checkIfUserExists()
                withContext(Dispatchers.Main) {
                    setToast("Welcome ${auth.currentUser!!.displayName}")
                    findNavController().navigate(R.id.action_splashFragment_to_chooseWeaponFragment)
                }


            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Log.e("ERROR", e.message.toString())
                }
            }
        }

    }

    override fun onStart() {
        super.onStart()
        auth = FirebaseAuth.getInstance()

        auth.currentUser?.let {
            loginViewModel.checkIfUserExists()
            findNavController().navigate(R.id.action_splashFragment_to_chooseWeaponFragment)
        }
    }

}