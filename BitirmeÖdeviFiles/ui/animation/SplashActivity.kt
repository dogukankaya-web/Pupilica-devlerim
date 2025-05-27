package com.example.pupilicabitirmeprojesi.ui.animation

import android.animation.Animator
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.airbnb.lottie.LottieAnimationView
import com.example.pupilicabitirmeprojesi.MainActivity
import com.example.pupilicabitirmeprojesi.R

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val lottieView = findViewById<LottieAnimationView>(R.id.lottieView)
        lottieView.setAnimation("kapi_acilma_animasyon.json")
        lottieView.playAnimation()

        lottieView.addAnimatorListener(object : Animator.AnimatorListener {
            override fun onAnimationStart(p0: Animator) {
            }

            override fun onAnimationEnd(p0: Animator) {
                lottieView.removeAllAnimatorListeners()
                lottieView.setAnimation("welcome_animasyon.json")
                lottieView.playAnimation()
                lottieView.addAnimatorListener(object : Animator.AnimatorListener {
                    override fun onAnimationStart(p0: Animator) {
                    }
                    override fun onAnimationEnd(p0: Animator) {
                        startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                        finish()
                    }
                    override fun onAnimationCancel(p0: Animator) {
                    }
                    override fun onAnimationRepeat(p0: Animator) {
                    }
                })
            }

            override fun onAnimationCancel(p0: Animator) {
            }

            override fun onAnimationRepeat(p0: Animator) {
            }
        })
    }
}
