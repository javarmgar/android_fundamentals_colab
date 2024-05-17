package com.example.androidfundamentalsapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import java.util.Stack
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

class EmailSenderActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_email_sender)

        val textMessage = "javo"
        val sendIntent = Intent()

//        sendIntent.action = Intent.ACTION_SEND
//        sendIntent.type = "text/plain"
//        sendIntent.putExtra(Intent.EXTRA_TEXT,textMessage)

        val lambda: Intent.() -> Unit = {
            action = Intent.ACTION_SEND
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT,textMessage)
        }
        sendIntent.apply(lambda)

        sendIntent.apply{
            action = Intent.ACTION_SEND
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT,textMessage)
        }

        val printNumber: () -> Unit = {

            -> println("havs")
        }
        textMessage.print()

        val pila: Stack<Int> = Stack<Int>()
        pila.push(2)
        pila.push(3)
        pila.push(1)

        val button:Button = findViewById(R.id.button)
        button.setOnClickListener{
            startActivity(sendIntent)
        }

    }

    fun String.printStandardFunction() {
        println(this)
    }

    fun String.printLambdaFunction() = {
        ->
        println(this)
    }
    val printLambda: String.() -> Unit = {
        ->
        println(this)

    }
    fun String.print() = printLambda

    fun standardMethod (){
        println("javi")
    }

    fun lambdaMethod() = {
        println("Jaby")
        println("Jaby")

    }
}