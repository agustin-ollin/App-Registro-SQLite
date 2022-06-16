package com.example.app_registro

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.TextView
import com.airbnb.lottie.LottieAnimationView
import android.os.Handler

/**
 * SplashScreen de la aplicación
 */
class SplashScreen : AppCompatActivity() {
    // Declaración de variables y componentes
    lateinit var appNme: TextView
    lateinit var lottie: LottieAnimationView

    /**
     * Función onCreate
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(R.layout.activity_splash_screen)

        appNme = findViewById(R.id.appName)
        lottie = findViewById(R.id.lottie)

        appNme.animate().translationY(2000f).setDuration(3200).setStartDelay(3300)
        lottie.animate().translationY(2000f).setDuration(3200).setStartDelay(3300)
        Handler().postDelayed(Runnable {
            val intent: Intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }, 5000)
    }
}