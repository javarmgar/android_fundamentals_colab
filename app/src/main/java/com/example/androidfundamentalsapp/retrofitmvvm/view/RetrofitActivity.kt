package com.example.androidfundamentalsapp.retrofitmvvm.view

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import com.example.androidfundamentalsapp.MyApplication
import com.example.androidfundamentalsapp.R
import com.example.androidfundamentalsapp.retrofitmvvm.model.remote.services.SpotifyAuthorizationService
import com.example.androidfundamentalsapp.retrofitmvvm.viewmodel.RetrofitActivityViewModel
import javax.inject.Inject

class RetrofitActivity : AppCompatActivity() {

    //Injected variables
    @Inject
    lateinit var spotifyAuthService: SpotifyAuthorizationService
    //Playlists variables
    private lateinit var buttonShowPlaylists: Button
    private lateinit var editTextUserId: EditText

    private lateinit var retrofitActivityViewModel: RetrofitActivityViewModel
    private lateinit var startOAuthButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        ( this.application as MyApplication).applicationContainer.inject(this)
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

        //user playlists
        editTextUserId = findViewById<EditText>(R.id.edit_text_user_id)
        buttonShowPlaylists = findViewById<Button>(R.id.button_show_playlists)

        //Observer
        retrofitActivityViewModel.isTokenAvailable.observe(this){
            if(it == true){
                editTextUserId.visibility = View.VISIBLE
                buttonShowPlaylists.visibility = View.VISIBLE
            }else{
                editTextUserId.visibility = View.GONE
                buttonShowPlaylists.visibility = View.GONE
            }
        }

        buttonShowPlaylists.setOnClickListener {
            retrofitActivityViewModel.onGetPlaylists(userId = editTextUserId.text.toString())
        }
    }


    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        Log.d(LOG_TAG, "handleNewIntent" )
        retrofitActivityViewModel.getCodeFromIntent(intent,spotifyAuthService)
    }



    companion object{

        val LOG_TAG = "RetrofitActivity"
    }
}