package com.dev.clevertonsantos.mybeats.ui.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.dev.clevertonsantos.mybeats.MainActivity
import com.dev.clevertonsantos.mybeats.R

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.R) {
            window.setDecorFitsSystemWindows(false)
        } else {
            @Suppress("DEPRECATION")
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
        }
        actionBar?.hide()
        setContentView(R.layout.activity_login)

        val button = findViewById<Button>(R.id.buttonEntrar)
        val text = findViewById<TextView>(R.id.textViewInscrevase)
        val intent = Intent(this, MainActivity::class.java)
        button.setOnClickListener {
            startActivity(intent)
        }
        text.setOnClickListener {
            Log.i("Teste", "clicou text")
        }
    }
}