package com.example.androidfundamentalsapp.retrofitActivity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.androidfundamentalsapp.R

class RetrofitActivity : AppCompatActivity() {
    private lateinit var startOAuthButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_retrofit)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        startOAuthButton = findViewById(R.id.start_oauth_button)
        startOAuthButton.setOnClickListener {
            Toast.makeText(this,"getting The OAuth code ", Toast.LENGTH_LONG).show()
            startAuthorization()
        }
    }

    private fun startAuthorization() {
        val clientId = "fdbc87739f9e45e894e2e0844542806b"
        val scopes = "user-read-private user-read-email"

        val authUri = Uri.Builder()
            .scheme("https")
            .authority("accounts.spotify.com")
            .appendPath("authorize")
            .appendQueryParameter("client_id", clientId)
            .appendQueryParameter("response_type", "code")
            .appendQueryParameter("redirect_uri", REDIRECT_URI)
            .appendQueryParameter("scope", scopes)
            .build()

        val intent = Intent(Intent.ACTION_VIEW, authUri)
        startActivity(intent)
    }

    override fun onResume() {
        super.onResume()
        handleNewIntent(source = "onResume", intent = null)
    }
    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        handleNewIntent(source = "onNewIntent", intent = intent)
    }

    private fun handleNewIntent(source: String, intent: Intent?) {
        Log.d(LOG_TAG, "handleNewIntent with source:$source" )
        intent?.data?.let { uri ->
            if (uri.toString().startsWith(REDIRECT_URI)) {
                val code = uri.getQueryParameter("code")
                val output = "The code is $code with source: $source"
                Log.d(LOG_TAG, output )
                Toast.makeText(this, output, Toast.LENGTH_LONG).show()
            }
        }
    }

    companion object{
        val REDIRECT_URI = "spotifychoreapp://callback"
        val LOG_TAG = "RetrofitActivity"
    }
}