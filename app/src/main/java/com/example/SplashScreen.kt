package com.example

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.notes.MainActivity
import com.example.notes.R
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.delay

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        supportActionBar?.hide()
        window.statusBarColor = Color.WHITE
        window.decorView.systemUiVisibility =
            View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR//  set status text dark
        window.statusBarColor =
            ContextCompat.getColor(this, R.color.white)// set status background white

        val job = GlobalScope.async {
            try {
                delay(2000)
            } catch (e: Exception) {
                Toast.makeText(applicationContext, "$e", Toast.LENGTH_SHORT).show()
            } finally {
                startActivity(Intent(applicationContext, MainActivity::class.java))
                finish()
            }
        }
        job.start()
    }
}