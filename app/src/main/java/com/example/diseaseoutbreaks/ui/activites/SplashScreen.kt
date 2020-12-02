package com.example.diseaseoutbreaks.ui.activites

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.diseaseoutbreaks.R
import com.example.diseaseoutbreaks.databinding.ActivitySplashScreenBinding
import com.example.diseaseoutbreaks.util.rotateAndFadeIn


@Suppress("DEPRECATION")
class SplashScreen : AppCompatActivity() {
    lateinit var binding: ActivitySplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this,
            R.layout.activity_splash_screen
        )

        binding.apply {
            splashImage.startAnimation(rotateAndFadeIn(this@SplashScreen,
                R.anim.splash_animation
            ))
            splashText.startAnimation(rotateAndFadeIn(this@SplashScreen,
                R.anim.splash_animation
            ))
        }

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