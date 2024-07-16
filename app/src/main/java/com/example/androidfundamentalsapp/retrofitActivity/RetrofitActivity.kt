package com.example.androidfundamentalsapp.retrofitActivity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.example.androidfundamentalsapp.R
import com.example.androidfundamentalsapp.viewmodel.RetrofitActivityViewModel

class RetrofitActivity : AppCompatActivity() {
    private lateinit var retrofitActivityViewModel: RetrofitActivityViewModel
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
        //ViewModel
        retrofitActivityViewModel = ViewModelProvider(this).get(RetrofitActivityViewModel::class.java)

        startOAuthButton = findViewById(R.id.start_oauth_button)
        startOAuthButton.setOnClickListener {
            Toast.makeText(this,"getting The OAuth code ", Toast.LENGTH_LONG).show()
            val intent = retrofitActivityViewModel.getAuthorizationIntent()
            startActivity(intent)
        }
    }


    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        Log.d(LOG_TAG, "handleNewIntent" )
        retrofitActivityViewModel.getCodeFromIntent(intent)
    }



    companion object{

        val LOG_TAG = "RetrofitActivity"
    }
}