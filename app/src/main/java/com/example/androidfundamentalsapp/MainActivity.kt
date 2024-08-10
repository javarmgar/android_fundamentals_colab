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
import com.example.androidfundamentalsapp.layouts.ConstraintLayoutActivity
import com.example.androidfundamentalsapp.layouts.LinearLayoutActivity
import com.example.androidfundamentalsapp.layouts.RelativeLayoutActivity
import com.example.androidfundamentalsapp.patterns.adapter.Client
import com.example.androidfundamentalsapp.patterns.observer.ConcreteObserver
import com.example.androidfundamentalsapp.patterns.observer.ConcreteObserverTwo
import com.example.androidfundamentalsapp.patterns.singleton.ClientSingleton
import com.example.androidfundamentalsapp.patterns.singleton.ClientSingletonV2
import com.example.androidfundamentalsapp.resourcesactivity.AppResourcesActivity
import com.example.androidfundamentalsapp.resourcesactivity.ResourcesActivity
import com.example.androidfundamentalsapp.retrofitmvvm.view.RetrofitActivity
import com.example.androidfundamentalsapp.stylesandthemes.StylesAndThemesActivity
import com.example.androidfundamentalsapp.viewmodel.MainActivityViewModel
import com.example.androidfundamentalsapp.viewsActivity.dynamicviewsactivity.DynamicViewsActivity
import com.example.androidfundamentalsapp.viewsActivity.staticviewactivity.EditTextActivity
import com.example.androidfundamentalsapp.viewsActivity.staticviewactivity.TextViewActivity
import java.util.Stack


class MainActivity : AppCompatActivity() {


    private lateinit var retrofitButton: Button
    private lateinit var dynamicViewsButton: Button
    private lateinit var stylesThemesButton: Button

    //Resources
    private lateinit var resourcesButton: Button
    private lateinit var appResourcesButton: Button

    //Views
    private lateinit var textViewActivityButton: Button
    private lateinit var editTextActivityButton: Button

    //Layouts
    private lateinit var constraintLayoutButton: Button
    private lateinit var relativeLayoutButton: Button
    private lateinit var linearLayoutButton: Button

    private lateinit var pickupContactButton: Button

    private lateinit var mainActivityViewModel: MainActivityViewModel
    var TAG = "MainActivity"
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
        setContentView(R.layout.activity_main)

        //ViewModel
        mainActivityViewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        //OBSERVER PATTERN
        val concreteObserver: ConcreteObserver = ConcreteObserver()
        val concreteObserverTwo: ConcreteObserverTwo = ConcreteObserverTwo()

        mainActivityViewModel.personaLiveData.observe(this){
            Log.d(TAG,"personaLiveData $it")
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

        /** VIEWMODEL EXAMPLE (Change Variable) **/
        /* Updating two variables. A local variable and a ViewModel variable,
         * display the variables in TextViews and see the lifecycle of each one
         */
        val changeVariableButton:Button = findViewById(R.id.changeVariableButton)
        val variableTextView:TextView =  findViewById(R.id.variable)
        val variableViewModelTextView:TextView = findViewById(R.id.viewModelTextView)

        changeVariableButton.setOnClickListener {
            variableTextView.text = "Havi"
            mainActivityViewModel.changeVariable("Havi")
            variableViewModelTextView.text = mainActivityViewModel.variable


        }
        variableViewModelTextView.text = mainActivityViewModel.variable


        /** ACTIVITY FOR RESULT EXAMPLE **/
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
        /** START OF LAYOUT SESSIONS**/

        linearLayoutButton = findViewById(R.id.linear_layout_button)
        relativeLayoutButton = findViewById(R.id.relative_layout_button)
        constraintLayoutButton = findViewById(R.id.constraint_layout_button)

        linearLayoutButton.setOnClickListener {
            val intentLinearLayout = Intent(this, LinearLayoutActivity::class.java)
            startActivity(intentLinearLayout)
        }

        relativeLayoutButton.setOnClickListener {
            val intentRelativeLayout = Intent(this, RelativeLayoutActivity::class.java)
            startActivity(intentRelativeLayout)
        }

        constraintLayoutButton.setOnClickListener {
            val intentConstraintLayout = Intent(this, ConstraintLayoutActivity::class.java)
            startActivity(intentConstraintLayout)
        }

        /** START OF VIEWS ACTIVITY SESSION**/
        //Text View
        textViewActivityButton = findViewById<Button>(R.id.views_button)
        textViewActivityButton.setOnClickListener {
            val intentViewsActivity = Intent(this, TextViewActivity::class.java)
            startActivity(intentViewsActivity)
        }

        //Edit Text
        editTextActivityButton = findViewById<Button>(R.id.edit_text_button)
        editTextActivityButton.setOnClickListener {
            val intentViewsActivity = Intent(this, EditTextActivity::class.java)
            startActivity(intentViewsActivity)
        }

        /** START OF RESOURCES*/
        resourcesButton = findViewById<Button>(R.id.resources_button)
        resourcesButton.setOnClickListener {
            val intentResourcesActivity = Intent(this, ResourcesActivity::class.java)
            startActivity(intentResourcesActivity)
        }

        //App Resources
        appResourcesButton = findViewById<Button>(R.id.app_resources_button)
        appResourcesButton.setOnClickListener {
            val intentAppResourcesActivity = Intent(this, AppResourcesActivity::class.java)
            startActivity(intentAppResourcesActivity)
        }

        //Styles and themes
        stylesThemesButton = findViewById<Button>(R.id.app_styles_and_themes_button)
        stylesThemesButton.setOnClickListener {
            val intentStylesThemesActivity = Intent(this, StylesAndThemesActivity::class.java)
            startActivity(intentStylesThemesActivity)
        }

        //views
        //dynamic views
        dynamicViewsButton = findViewById<Button>(R.id.dynamic_views_button)
        dynamicViewsButton.setOnClickListener {
            val intent = Intent(this, DynamicViewsActivity::class.java)
            startActivity(intent)
        }

        //retrofit
        retrofitButton = findViewById<Button>(R.id.retrofit_button)
        retrofitButton.setOnClickListener {
            val intent = Intent(this, RetrofitActivity::class.java)
            startActivity(intent)
        }

        /** DESIGN PATTERNS*/
        setDesignPatterns()
    }

    private fun setDesignPatterns() {
        setAdapterPattern()
        // Singleton Pattern
        setSingletonPattern()
    }

    private fun setSingletonPattern() {
        ClientSingleton.main()
        //Kotlin
        ClientSingletonV2.main()

    }

    private fun setAdapterPattern() {

        val applicationContainer = (application as MyApplication).applicationContainer
        // Adapter
        val client:Client = applicationContainer.client()
        client.methodName()

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