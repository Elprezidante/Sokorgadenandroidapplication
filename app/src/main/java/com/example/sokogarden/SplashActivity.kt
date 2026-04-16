package com.example.sokogarden

import android.content.Intent
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val logo = findViewById<ImageView>(R.id.logo)
        val spinAnimation = AnimationUtils.loadAnimation(this, R.anim.spin_in_out)

        logo.startAnimation(spinAnimation)

        // Duration of total animation is 3000ms (1000 spin in + 1000 wait + 1000 spin out)
        logo.postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, 3000)
    }
}