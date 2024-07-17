package com.example.androidfundamentalsapp.retrofitmvvm.viewmodel

import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.androidfundamentalsapp.retrofitmvvm.model.remote.RemoteConstants
import com.example.androidfundamentalsapp.retrofitmvvm.model.remote.WebServiceManager
import com.example.androidfundamentalsapp.retrofitmvvm.model.remote.services.response.GetPlaylistResponse
import com.example.androidfundamentalsapp.retrofitmvvm.model.remote.services.response.TokenResponse
import com.example.androidfundamentalsapp.retrofitmvvm.view.RetrofitActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.HttpURLConnection

class RetrofitActivityViewModel:ViewModel() {
    private lateinit var _accessToken: String
    val LOG_TAG = "RetrofitActivityViewModel"

    //Observee variable
    private val _isTokenAvailable: MutableLiveData<Boolean> = MutableLiveData(false)
    val isTokenAvailable: LiveData<Boolean> = _isTokenAvailable

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
                    onGetAccessToken(code = it)
                }
            }
        }
    }

    fun onGetAccessToken(code: String){
        val spotifyAuthService = WebServiceManager.getSpotifyAuthorizationService()

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
                        tokenResponse?.let {
                            onTokenObtained(it.accessToken)
                        }
                        Log.d(LOG_TAG,"tokenResponse: $tokenResponse")
                    }
                    else -> {
                        Log.d(LOG_TAG,"tokenResponse: $response")
                    }
                }

            }

            override fun onFailure(call: Call<TokenResponse>, t: Throwable) {

                // Client Error
                Log.d(LOG_TAG,"onFailure $t")
            }

        })
    }

    private fun onTokenObtained(accessToken: String) {
        _accessToken = accessToken
        _isTokenAvailable.value = true

    }

    fun onGetPlaylists(userId: String) {
        val spotifyAPIService = WebServiceManager.getSpotifyAPIService()
        val authorizationValue = "Bearer $_accessToken"

        val callAccessToken: Call<GetPlaylistResponse> = spotifyAPIService.getUserPlaylists(
            userId = userId,
            authorization = authorizationValue
        )

        callAccessToken.enqueue(object : Callback<GetPlaylistResponse>{
            override fun onResponse(
                call: Call<GetPlaylistResponse>,
                response: Response<GetPlaylistResponse>
            ) {
                when (response.code()) {
                    HttpURLConnection.HTTP_OK -> {

                        val getPlaylistResponse=response.body()
                        getPlaylistResponse?.let { r ->
                            r.itemList.forEach {
                                Log.d(LOG_TAG, it.toString())
                            }
                        }
                    }
                    else -> {
                        Log.d(LOG_TAG,"response: $response")
                    }
                }
            }

            override fun onFailure(call: Call<GetPlaylistResponse>, t: Throwable) {
                // Client Error
                Log.d(LOG_TAG,"onFailure $t")
            }

        })

    }


}
