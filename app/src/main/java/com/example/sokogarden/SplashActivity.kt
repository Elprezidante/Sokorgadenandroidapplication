package com.example.sokogarden

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val logo = findViewById<ImageView>(R.id.logo)
        val tagline = findViewById<TextView>(R.id.tagline)
        
        // Spin and Scale animation for logo
        val spinAnimation = AnimationUtils.loadAnimation(this, R.anim.spin_in_out)
        logo.startAnimation(spinAnimation)

        // Fade in animation for the tagline after logo starts spinning
        logo.postDelayed({
            tagline.visibility = View.VISIBLE
            tagline.animate().alpha(1.0f).setDuration(1000).start()
        }, 1000)

        // Navigate to MainActivity after 4 seconds total
        logo.postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
            finish()
        }, 4000)
    }
}