package com.example.androidfundamentalsapp.viewsActivity.staticviewactivity

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.androidfundamentalsapp.R

class TextViewActivity : AppCompatActivity() {
    private lateinit var textView3: TextView
    private lateinit var textView2: TextView
    private lateinit var editTextViews: EditText
    private lateinit var changeTextButton: Button
    private lateinit var textView1: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_text_view)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        textView1 = findViewById<TextView>(R.id.textView1)
        textView2 = findViewById<TextView>(R.id.text_view_2)
        textView3 = findViewById<TextView>(R.id.text_view_3)
        changeTextButton = findViewById<Button>(R.id.change_text_button)
        editTextViews = findViewById<EditText>(R.id.edit_text_1)
        textView2.isSelected = true
        setListeners()


    }

    private fun setListeners() {
        changeTextButton.setOnClickListener {
            textView1.text = "Programmatically text"
        }

        /***
         * Edit Text Listener:
         * Used to update distinct text views
         */
        editTextViews.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                textView1.text = s.toString()
                textView2.text = s.toString()
                textView3.text = s.toString()
            }

            override fun afterTextChanged(s: Editable?) {

            }

        })
    }
}