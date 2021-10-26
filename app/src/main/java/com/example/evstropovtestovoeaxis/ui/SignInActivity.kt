package com.example.evstropovtestovoeaxis.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.evstropovtestovoeaxis.databinding.ActivitySignInBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import android.util.Log
import com.google.android.gms.tasks.Task
import com.google.android.gms.common.api.ApiException

class SignInActivity : AppCompatActivity() {
    lateinit var binding: ActivitySignInBinding
    val RC_SIGN_IN = 0
    val mGoogleSignInClient:GoogleSignInClient by lazy{
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()
        GoogleSignIn.getClient(this, gso)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignInBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val acct = GoogleSignIn.getLastSignedInAccount(this)
        if (acct != null) {
            goToUserActivity()
        }

        binding.signInButton.setOnClickListener {
            signIn()
        }
    }
    private fun signIn() {
        val signInIntent = mGoogleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN) {

            val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)
        }
    }
    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account = completedTask.getResult(ApiException::class.java)

            goToUserActivity()
        } catch (e: ApiException) {
            Log.w("ooo", "signInResult:failed code=" + e.statusCode)
        }
    }
    private fun goToUserActivity() {
        val intent = Intent(this, NotesActivity::class.java)
        finish()
        startActivity(intent)
    }
}