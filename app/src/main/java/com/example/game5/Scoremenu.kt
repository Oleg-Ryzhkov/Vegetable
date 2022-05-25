package com.example.game5

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class Scoremenu : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scoremenu)

        val res = findViewById<Button>(R.id.res)
        val scor = findViewById<TextView>(R.id.Score)


        val arguments = intent.extras
        val thos = arguments!!["int"].toString()
        scor.text = "Score: "+ thos
        res.setOnClickListener() {
            val resss = Intent(this, Multigame::class.java)
            startActivity(resss)
            finish()
        }

    }
}