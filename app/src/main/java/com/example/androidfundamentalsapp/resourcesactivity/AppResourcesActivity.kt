package com.example.androidfundamentalsapp.resourcesactivity

import android.media.Image
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.androidfundamentalsapp.R

class AppResourcesActivity : AppCompatActivity() {
    private lateinit var churrosImageView: ImageView
    private lateinit var stringArrayTextView: TextView
    private lateinit var integerArrayTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_app_resources)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val integerArray: IntArray =resources.getIntArray(R.array.example_integer)
        var text:String = ""
        integerArray.forEach {
            text += it.toString() + " "
        }

        integerArrayTextView = findViewById<TextView>(R.id.int_array_textview)
        integerArrayTextView.text = text

        val stringArray: Array<String> =resources.getStringArray(R.array.example_string)
        val stringText = stringArray.fold("") { acc: String, it: String ->
            "$acc$it "
        }

        stringArrayTextView = findViewById<TextView>(R.id.string_array_textview)
        stringArrayTextView.text = stringText

        churrosImageView = findViewById<ImageView>(R.id.churros_imageview)
        val visibilityChurrosFlag = resources.getBoolean(R.bool.isPortrait)
        if(!visibilityChurrosFlag)
            churrosImageView.visibility = View.GONE


    }
}