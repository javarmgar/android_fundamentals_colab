package com.example.androidfundamentalsapp.views

import android.os.Bundle
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.ActivityChooserView.InnerLayout
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.androidfundamentalsapp.R

class EditTextActivity : AppCompatActivity() {
    private lateinit var emailButton: Button
    private lateinit var numberButton: Button
    private lateinit var passwordButton: Button
    private lateinit var textButton: Button

    private lateinit var editTextCallbacks: EditText
    private lateinit var editTextInputType: EditText
    private val TAG = "EditTextActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_edit_text)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //Callbacks
        editTextCallbacks = findViewById<EditText>(R.id.edit_text_callbacks)
        editTextCallbacks.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                Log.d(TAG,"beforeTextChanged $s")
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                Log.d(TAG,"onTextChanged $s")
            }

            override fun afterTextChanged(s: Editable?) {
                Log.d(TAG,"afterTextChanged $s")
            }

        })

        //InputType
        editTextInputType = findViewById(R.id.edit_text_input_type)
        textButton = findViewById<Button>(R.id.text_button)
        passwordButton = findViewById<Button>(R.id.password_button)
        numberButton = findViewById<Button>(R.id.number_button)
        emailButton = findViewById<Button>(R.id.email_button)
        setListeners()


    }

    private fun setListeners() {
        textButton.setOnClickListener {
            editTextInputType.setText("")
            editTextInputType.inputType = InputType.TYPE_CLASS_TEXT
        }
        passwordButton.setOnClickListener {
            editTextInputType.setText("")
            editTextInputType.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
        }
        numberButton.setOnClickListener {
            editTextInputType.setText("")
            editTextInputType.inputType = InputType.TYPE_CLASS_NUMBER
        }
        emailButton.setOnClickListener {
            editTextInputType.setText("")
            editTextInputType.inputType = InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS
        }
    }


}