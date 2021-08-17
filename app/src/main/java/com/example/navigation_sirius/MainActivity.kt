package com.example.navigation_sirius

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions

class MainActivity : AppCompatActivity() {

    private val buttonPush by lazy(LazyThreadSafetyMode.NONE) { findViewById<Button>(R.id.btnPush) }

    private val loginResultHandler =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult? ->

            val task = GoogleSignIn.getSignedInAccountFromIntent(result?.data)
            val account = task.result

            startSecondActivity(account)
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        buttonPush.setOnClickListener {
            loginResultHandler.launch(getSignInIntent())
        }
    }

    override fun onStart() {
        super.onStart()

        val account = GoogleSignIn.getLastSignedInAccount(this)
        startSecondActivity(account)
    }

    private fun getSignInIntent(): Intent {

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()

        val mGoogleSignInClient = GoogleSignIn.getClient(this, gso)

        return mGoogleSignInClient.signInIntent
    }

    private fun startSecondActivity(account: GoogleSignInAccount?) {
        if (account != null) {
            buttonPush.visibility = View.INVISIBLE

            // start second activity
        }
    }
}
