package com.dicoding.animalkuiz

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.dicoding.animalkuiz.R

class Splash : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // Get the ImageView from the layout
        val imageView = findViewById<ImageView>(R.id.imageView4)

        // Load the fade-in animation from the anim resource
        val fadeInAnimation = AnimationUtils.loadAnimation(this, R.anim.anim)

        // Apply the animation to the ImageView
        imageView.startAnimation(fadeInAnimation)

        // Set a listener on the animation
        fadeInAnimation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation?) {
                // Do nothing on animation start
            }

            override fun onAnimationEnd(animation: Animation?) {
                // Navigate to LandingActivity when the animation ends
                val intent = Intent(this@Splash, ActivityLanding::class.java)
                startActivity(intent)

                // Close the Splash activity so it's removed from the back stack
                finish()
            }

            override fun onAnimationRepeat(animation: Animation?) {
                // Do nothing on animation repeat
            }
        })
    }
}
