package com.example.androidfundamentalsapp

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.androidfundamentalsapp.PickUpContactActivity.Companion.ADDRESS_KEY
import com.example.androidfundamentalsapp.PickUpContactActivity.Companion.LAST_NAME_KEY
import com.example.androidfundamentalsapp.PickUpContactActivity.Companion.NAME_KEY
import patterns.observer.ConcreteObserver
import patterns.observer.ConcreteObserverTwo
import java.util.Stack


class EmailSenderActivity : AppCompatActivity() {
    private lateinit var pickupContactButton: Button

    private lateinit var mainActivityViewModel: MainActivityViewModel
    var TAG = "EmailSenderActivity"
    private lateinit var button: Button
    var flag:Boolean = false

    private val address:String = "Lago Xapala 47".apply {
        Log.d(TAG,this)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        Log.d(TAG,"onSaveInstanceState()")
        super.onSaveInstanceState(outState)
    }


    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        Log.d(TAG,"onRestoreInstanceState()")
        super.onRestoreInstanceState(savedInstanceState)
    }
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_email_sender)

        //ViewModel
        mainActivityViewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        //OBSERVER PATTERN
        val concreteObserver: ConcreteObserver = ConcreteObserver()
        val concreteObserverTwo: ConcreteObserverTwo = ConcreteObserverTwo()


        mainActivityViewModel.personaLiveData.observe(this){
            Log.d(TAG,"$it")
        }


        mainActivityViewModel.observee.addObserver(concreteObserver)
        mainActivityViewModel.observee.addObserver(concreteObserverTwo)

        val buttonUpdateObservee: Button = findViewById(R.id.buttonUpdateObservee)
        buttonUpdateObservee.setOnClickListener {
            mainActivityViewModel.updateObservee()
            mainActivityViewModel.updateLiveData()
        }



        Log.d(TAG, "onCreate()")


        Log.d(TAG,mainActivityViewModel.getDatabaseName())
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

        sendIntent.run{
            action = Intent.ACTION_SEND
            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT,textMessage)
        }

        var name = "Jabier"
        var fullName = name.run{
            this + " Harmentha"
        }.run{
            this + " Garzia"
        }
        Log.d(TAG,fullName)

        (fullName + address).apply {
            Log.d(TAG,this)
        }

        val printNumber: () -> Unit = {
            -> println("havs")
        }
        textMessage.print()

        val pila: Stack<Int> = Stack<Int>()
        pila.push(2)
        pila.push(3)
        pila.push(1)

        button = findViewById(R.id.button)
        button.setOnClickListener{
            startActivity(sendIntent)
        }

        val finishButton:Button = findViewById(R.id.button2)
        finishButton.setOnClickListener{
            finish()
            flag = true
        }

        val changeVariableButton:Button = findViewById(R.id.changeVariableButton)
        val variableTextView:TextView =  findViewById(R.id.variable)
        val variableViewModelTextView:TextView = findViewById(R.id.viewModelTextView)
        changeVariableButton.setOnClickListener {
            variableTextView.text = "Havi"
            mainActivityViewModel.changeVariable("Havi")
            variableViewModelTextView.text = mainActivityViewModel.variable

        }
        variableViewModelTextView.text = mainActivityViewModel.variable


        // START ACTIVITY FOR RESULT EXAMPLE
        pickupContactButton = findViewById(R.id.pickUpButton)
        pickupContactButton.setOnClickListener {
            val pickUpContactIntent = Intent(this ,PickUpContactActivity::class.java).apply{
                putExtra(CONTACT_ID_KEY,3)
            }
            startActivityForResult(
                pickUpContactIntent,
                PICK_UP_CONTACT_REQUEST_ID
            )
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode){
            PICK_UP_CONTACT_REQUEST_ID -> {
                Log.d(TAG,"onActivityResult() - 'PICK_UP_CONTACT_REQUEST_ID'")
                when(resultCode){
                    RESULT_OK -> {
                        data?.let {
                            Log.d(TAG,data.getStringExtra(NAME_KEY)?:"")
                            Log.d(TAG,data.getStringExtra(LAST_NAME_KEY)?:"")
                            Log.d(TAG,data.getStringExtra(ADDRESS_KEY)?:"")
                        }
                    }
                    RESULT_CANCELED ->{
                        Log.d(TAG,"RESULT_CANCELED")
                    }

                }
            }
            else ->{
                Log.d(TAG,"onActivityResult() - 'UNKNOWN'")

            }
        }
    }


    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart()")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume()")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause()")
    }


    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "onRestart()")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop()")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy()")
        if(flag){
            Log.d(TAG,"finish() called")
        }else{
            Log.d(TAG,"other exit")
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


    fun test() {

    }

    companion object{
        private val PICK_UP_CONTACT_REQUEST_ID = 42
        val CONTACT_ID_KEY = "CONTACT_ID_KEY"
    }
}