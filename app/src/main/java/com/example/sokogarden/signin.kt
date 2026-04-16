package com.example.sokogarden

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.loopj.android.http.RequestParams

class Signin : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_signin)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Find the UI elements by their IDs
        val email = findViewById<EditText>(R.id.email)
        val password = findViewById<EditText>(R.id.password)
        val signinBtn = findViewById<Button>(R.id.signinBtn)
        val signuptxt = findViewById<TextView>(R.id.singuptxt)

        // Navigate to Signup activity
        signuptxt.setOnClickListener {
            val intent = Intent(this, Signup::class.java)
            startActivity(intent)
        }

        // Handle sign-in logic
        signinBtn.setOnClickListener {
            // Specify the API endpoint
            val api = "http://elprezidante.alwaysdata.net/api/login"

            // Create a RequestParams object
            val data = RequestParams()

            // Add the email and the password
            data.put("email", email.text.toString())
            data.put("password", password.text.toString())

            // Initialize the API helper
            val helper = ApiHelper(this)

            // Post the login data
            helper.postLogin(api, data)
        }
    }
}