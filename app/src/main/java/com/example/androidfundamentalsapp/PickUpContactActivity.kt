package com.example.androidfundamentalsapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button

class PickUpContactActivity : AppCompatActivity() {

    private val TAG = "PickUpContactActivity"
    private lateinit var backButton:Button
    private lateinit var cancelButton:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pick_up_contact)
        Log.d(TAG,"onCreate()")

        //StartActivityForResult Example

        val contactIdFromIntent = intent.getIntExtra(MainActivity.CONTACT_ID_KEY,0)
        Log.d(TAG, "Contact Id: $contactIdFromIntent")
        //NAME /LAST NAME /ADDRESS
        val pickUpIntentResult = Intent().apply {
            putExtra(NAME_KEY,"Habyer")
            putExtra(LAST_NAME_KEY,"Sheimabaum")
            putExtra(ADDRESS_KEY,"XAPALA 47")
        }

        backButton = findViewById(R.id.back_button)
        backButton.setOnClickListener {
            setResult(RESULT_OK,pickUpIntentResult)
            finish()
        }
        cancelButton = findViewById(R.id.cancel_button)
        cancelButton.setOnClickListener {
            setResult(RESULT_CANCELED)
            finish()
        }


    }

    companion object{
        val NAME_KEY = "NAME_KEY"
        val LAST_NAME_KEY = "LAST_NAME_KEY"
        val ADDRESS_KEY = "ADDRESS_KEY"
    }
}