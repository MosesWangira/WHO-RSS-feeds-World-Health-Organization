package com.example.diseaseoutbreaks.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import com.example.diseaseoutbreaks.R

class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        val splashAnimator = AnimationUtils.loadAnimation(
            this,
            R.anim.splash_animation
        )

        val splashImage: ImageView = findViewById(R.id.splashImage)
        val splashText: TextView = findViewById(R.id.splashText)

        splashImage.startAnimation(splashAnimator)
        splashText.startAnimation(splashAnimator)


        val toLogin = Intent(this, MainActivity::class.java)
        //running on thread
        val timer = object : Thread() {
            override fun run() {
                try {
                    sleep(5000)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                } finally {
                    startActivity(toLogin)
                    finish()
                }
            }
        }
        timer.start()
    }
}