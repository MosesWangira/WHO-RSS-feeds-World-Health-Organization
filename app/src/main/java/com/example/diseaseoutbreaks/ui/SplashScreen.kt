package com.example.diseaseoutbreaks.ui

import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.palette.graphics.Palette
import com.example.diseaseoutbreaks.R
import com.example.diseaseoutbreaks.databinding.ActivitySplashScreenBinding


@Suppress("DEPRECATION")
class SplashScreen : AppCompatActivity() {
    lateinit var binding: ActivitySplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_splash_screen)

        val splashAnimator = AnimationUtils.loadAnimation(
            this,
            R.anim.splash_animation
        )

        val splashImage: ImageView = findViewById(R.id.splashImage)
        val splashText: TextView = findViewById(R.id.splashText)

        splashImage.startAnimation(splashAnimator)
        splashText.startAnimation(splashAnimator)

//        val bitmap = BitmapFactory.decodeResource(resources, R.drawable.who_image)
//
//        Palette.Builder(bitmap).generate { it?.let { palette ->
//            val dominantColor = palette.getDominantColor(ContextCompat.getColor(this, R.color.white))
//            binding.splashBackground.setBackgroundColor(ContextCompat.getColor(this, dominantColor))
//            Log.d("Color", dominantColor.toString())
//        } }


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