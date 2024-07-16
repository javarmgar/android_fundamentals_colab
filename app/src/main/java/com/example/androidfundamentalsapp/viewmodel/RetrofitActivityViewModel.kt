package com.example.androidfundamentalsapp.viewmodel

import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.androidfundamentalsapp.retrofitActivity.RetrofitActivity
import com.example.androidfundamentalsapp.retrofitActivity.data.RemoteConstants
import com.example.androidfundamentalsapp.retrofitActivity.data.WebServiceManager
import com.example.androidfundamentalsapp.retrofitActivity.data.services.response.TokenResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.HttpURLConnection

class RetrofitActivityViewModel:ViewModel() {
    val LOG_TAG = "RetrofitActivityViewModel"
    private var code: String? = null

    fun getAuthorizationIntent(): Intent {

        // step 1 start
        val scopes = "user-read-private user-read-email"

        val authUri = Uri.Builder()
            .scheme("https")
            .authority("accounts.spotify.com")
            .appendPath("authorize")
            .appendQueryParameter("client_id", RemoteConstants.CLIENT_ID)
            .appendQueryParameter("response_type", "code")
            .appendQueryParameter("redirect_uri", RemoteConstants.REDIRECT_URI)
            .appendQueryParameter("scope", scopes)
            .build()

        return Intent(Intent.ACTION_VIEW, authUri)
    }

    fun getCodeFromIntent(intent: Intent?) {

        intent?.data?.let { uri: Uri ->
            if (uri.toString().startsWith(RemoteConstants.REDIRECT_URI)) {
                code = uri.getQueryParameter("code")
                val output = "The code is $code "
                Log.d(RetrofitActivity.LOG_TAG, output)
                code?.let {
                    getAccessToken(code = it)
                }
            }
        }
    }

    fun getAccessToken(code: String){
        val spotifyAuthService = WebServiceManager.getSpotifyAuthService()
//        val tokenRequest = TokenRequest(
//            code = code,
//            grantType = "authorization_code",
//            redirectUri = RemoteConstants.REDIRECT_URI
//        )
        val callAccessToken: Call<TokenResponse> = spotifyAuthService.getAccessToken(
            code = code,
            grantType = "authorization_code",
            redirectUri = RemoteConstants.REDIRECT_URI
        )
        callAccessToken.enqueue(object : Callback<TokenResponse> {
            override fun onResponse(call: Call<TokenResponse>, response: Response<TokenResponse>) {

                when (response.code()) {
                    HttpURLConnection.HTTP_OK -> {

                        val tokenResponse=response.body()
                        Log.d(LOG_TAG,"tokenResponse: $tokenResponse")
                    }
                    else -> {
                        Log.d(LOG_TAG,"tokenResponse: $response")
                    }
                }

            }

            override fun onFailure(call: Call<TokenResponse>, t: Throwable) {

                // CLient Error
                Log.d(LOG_TAG,"onFailure $t")
            }

        })
    }




}
