package com.minjaeheo.todolistapp.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.minjaeheo.todolistapp.R

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        Handler(Looper.getMainLooper()).postDelayed(Runnable {
            startActivity(Intent(this, ListMainActivity::class.java)) // 지정한 액티비티로 이동
            finish()
        }, 1500)
    }
}