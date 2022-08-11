package com.example.testapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.testapp.auth.Login

class SplachScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
            Handler().postDelayed({
                val intent = Intent(this, Login::class.java)
                startActivity(intent)
                finish()
            },4500)
    }
}