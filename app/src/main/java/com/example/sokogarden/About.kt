package com.example.sokogarden

import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.util.Locale

class About : AppCompatActivity() {
//    Declare variable that will hold the text to speech object
    lateinit var tts: TextToSpeech


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_about)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
//        Find the text view and the button by use of their ids
        val textview = findViewById<TextView>(R.id.aboutTxt)
        val btnlisten = findViewById<Button>(R.id.btnlisten)
        
        
        tts = TextToSpeech(this) { status ->
            if (status == TextToSpeech.SUCCESS) {
                // Set language to English (US) as default for Sokogarden
                val result = tts.setLanguage(Locale.GERMAN)
                if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                    // Handle language not supported
                }
            }
        }

        btnlisten.setOnClickListener {
            val text = textview.text.toString()
            if (text.isNotEmpty()) {
                tts.speak(text, TextToSpeech.QUEUE_FLUSH, null, null)
            }
        }
    }

    override fun onDestroy() {
        if (::tts.isInitialized) {
            tts.stop()
            tts.shutdown()
        }
        super.onDestroy()
    }
}
