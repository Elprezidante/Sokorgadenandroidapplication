package com.example.sokogarden

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ProgressBar
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
//        find the buttons by use of their ids
        val signupbtn = findViewById<Button>(R.id.signupbtn)
        val signinbtn = findViewById<Button>(R.id.signinbtn)
//create the intent to the two activities
        signupbtn.setOnClickListener {
            val intent = Intent(applicationContext, Signup::class.java)
            startActivity(intent)
        }
//        ================================================================
        signinbtn.setOnClickListener {
            val intent = Intent(applicationContext, Signin::class.java)
            startActivity(intent)

        }

//        Find the recycler view and the progress bar by use of their ids
        val recyclerview = findViewById<RecyclerView>(R.id.recyclerview)
        val progressbar = findViewById<ProgressBar>(R.id.progressbar)

//        Specify the API url endpoint  for fetching products (alwaysdata)
        val url="http://elprezidante.alwaysdata.net/api/get_products"
//        import the helper class
        val helper = ApiHelper(applicationContext)
//        Inside the helper class access the function loadproducts
        helper.loadProducts(url, recyclerview, progressbar)

//      Find the about button by use of its ID and have the intent
val aboutButton = findViewById<Button>(R.id.aboutbtn)

//        Below is the intent to the about page
        aboutButton.setOnClickListener {
            val intent = Intent (applicationContext, About::class.java)
            startActivity(intent)
        }


    }

    }
