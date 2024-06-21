package com.example.androidfundamentalsapp.layouts

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.androidfundamentalsapp.R

class ConstraintLayoutActivity : AppCompatActivity() {
    private lateinit var visibilityView1: View
    private lateinit var visibilityButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_constraint_layout)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // VISIBILITY EXAMPLE

        var i = 0
        val visibilityArray = listOf(
            View.INVISIBLE,
            View.VISIBLE,
            View.GONE,
            View.INVISIBLE
        )
        visibilityButton = findViewById<Button>(R.id.visibility_button)
        visibilityView1 = findViewById<View>(R.id.visibility_view)
        visibilityButton.setOnClickListener {
            visibilityView1.visibility = visibilityArray[i]
            i++
            if(i == 4) i = 0
        }
    }
}