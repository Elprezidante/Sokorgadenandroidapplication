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

class Signup : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_signup)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Find the UI elements by their IDs
        val username = findViewById<EditText>(R.id.username)
        val email = findViewById<EditText>(R.id.email)
        val password = findViewById<EditText>(R.id.password)
        val phoneNumber = findViewById<EditText>(R.id.phoneNumber)
        val signupBtn = findViewById<Button>(R.id.signupBtn)
        val signintxt = findViewById<TextView>(R.id.signintxt)

        // Navigate back to Signin activity
        signintxt.setOnClickListener {
            val intent = Intent(this, Signin::class.java)
            startActivity(intent)
        }

        // Handle signup logic
        signupBtn.setOnClickListener {
            val api = "http://elprezidante.alwaysdata.net/api/signup"
//            Create a request params(This is where we shall hold all the data)
            val data = RequestParams()
            data.put("username", username.text.toString().trim())
            data.put("email", email.text.toString().trim())
            data.put("password", password.text.toString().trim())
            data.put("phone", phoneNumber.text.toString().trim())

            val helper = ApiHelper(this)
            // Assuming your ApiHelper has a generic post method for signup
            helper.post(api, data)
        }
    }
}