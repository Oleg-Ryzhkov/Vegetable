package com.example.game5

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class LoadMenu : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_load_menu)
        Handler().postDelayed({
            val manu = Intent(this, Menuofgame::class.java)
            startActivity(manu)
            finish()
        },3000)
    }
}