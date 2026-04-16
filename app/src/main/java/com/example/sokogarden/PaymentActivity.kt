package com.example.sokogarden // Defines the package name for the application

import android.os.Bundle // Imports the Bundle class to handle saved state
import android.widget.Button // Imports the Button widget
import android.widget.EditText // Imports the EditText widget for user input
import android.widget.ImageView // Imports the ImageView widget to display pictures
import android.widget.TextView // Imports the TextView widget to display text
import androidx.activity.enableEdgeToEdge // Imports the utility to enable edge-to-edge display
import androidx.appcompat.app.AppCompatActivity // Imports the base class for activities using modern features
import androidx.core.view.ViewCompat // Imports ViewCompat for backward-compatible view operations
import androidx.core.view.WindowInsetsCompat // Imports WindowInsetsCompat to handle system bars (status/nav bars)
import com.bumptech.glide.Glide // Imports Glide library for efficient image loading
import com.loopj.android.http.RequestParams // Imports RequestParams to handle data for network requests

class PaymentActivity : AppCompatActivity() { // Declares the PaymentActivity class inheriting from AppCompatActivity
    override fun onCreate(savedInstanceState: Bundle?) { // The function that runs when the activity is first created
        super.onCreate(savedInstanceState) // Calls the parent class's onCreate method
        enableEdgeToEdge() // Configures the app to display content behind system bars (full screen)
        setContentView(R.layout.activity_payment) // Links the activity to its XML layout file
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets -> // Adjusts the view padding to account for system bars
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars()) // Gets the sizes of system status and navigation bars
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom) // Applies the system bar sizes as padding to the view
            insets // Returns the original insets object
        }

//        Find the views by use of their ids
        val txtname = findViewById<TextView>(R.id.txtProductName) // Links the txtname variable to the TextView in layout
        val textcost = findViewById<TextView>(R.id.txtProductCost) // Links the textcost variable to the TextView in layout
        val imgproduct = findViewById<ImageView>(R.id.imgProduct) // Links the imgproduct variable to the ImageView in layout

//        Retrieve the data passed from the previous activity(MainActivity)
        val productName = intent.getStringExtra("product_name") // Gets the product name string sent through the Intent
//For  any integer you ought to Pass a default value just incase it misses
        val cost = intent.getIntExtra("product_cost", 0) // Gets the product cost integer, using 0 as a backup if not found
        val productPhoto = intent.getStringExtra("product_photo") // Gets the product photo filename sent through the Intent


// Update the text views with the data passed from the previous activity
        txtname.text = productName // Displays the retrieved product name in the TextView
        textcost.text = "Ksh $cost" // Displays the cost formatted with "Ksh" currency in the TextView
//        Specify the image url
         val imageurl = "https://elprezidante.alwaysdata.net/static/images/$productPhoto" // Builds the full URL to the product image
        //Load image using Glide, Load Faster with Glide
        Glide.with(this) // Initializes Glide for this specific activity context
            .load(imageurl) // Specifies the URL of the image to be downloaded
            .placeholder(R.drawable.ic_launcher_background) // Shows a default image while the real one is loading
            .into(imgproduct) // Tells Glide to place the downloaded image into our ImageView
//Find the edit text and pay now btn by use of their ids
        val phone = findViewById<EditText>(R.id.phone) // Links the phone variable to the EditText input field
        val btnpay = findViewById<Button>(R.id.pay) // Links the btnpay variable to the payment button in layout

//        Set onclick listener on the pay now button
        btnpay.setOnClickListener { // Defines what happens when the user clicks the "Pay Now" button

//            Specify the api endpoint for making payments
            val api = "https://kbenkamotho.alwaysdata.net/api/mpesa_payment" // The web address where payment data is sent

// Create a Request params
            val data = RequestParams() // Creates a new container to hold the payment data


            data.put("amount", cost) // Adds the product cost to the data package
            data.put("Phone", phone.text.toString().trim()) // Adds the cleaned phone number (no spaces) to the data package

//            Import the helper class
            val helper = ApiHelper(applicationContext) // Creates an instance of our ApiHelper to handle the network task

//            access the post method
            helper.post(api, data) // Sends the payment data to the API using the POST method

//            clear the phone  number from the edit text
            phone.text.clear() // Empties the phone number field after the payment request is sent


        }

    }
}