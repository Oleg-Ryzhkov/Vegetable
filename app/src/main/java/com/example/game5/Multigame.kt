package com.example.game5

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Rect
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.ImageView
import android.widget.TextView

class Multigame : AppCompatActivity() {
    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val good1 = findViewById<ImageView>(R.id.good1)
        val good2 = findViewById<ImageView>(R.id.good2)
        val bad1 = findViewById<ImageView>(R.id.bad1)
        val bad2 = findViewById<ImageView>(R.id.bad2)
        val goodbuy = findViewById<ImageView>(R.id.goodbuy)
        val badout = findViewById<ImageView>(R.id.badout)
        val scoretext = findViewById<TextView>(R.id.textView)
        val livetext = findViewById<TextView>(R.id.textView2)
        var live = 3
        var score = 0

        val position = arrayOf(-400, -200, 0, 200, 400)

        fun bad1down() {
            bad1.animate().translationY(-800f).translationX(position[0].toFloat()).setDuration(0)
                .withEndAction() {
                    bad1.animate().translationYBy(3000f).setDuration(5000).withEndAction() {
                        bad1down()
                    }

                }
        }

        fun bad2down() {
            bad2.animate().translationY(-1000f).translationX(position[1].toFloat()).setDuration(0)
                .withEndAction() {
                    bad2.animate().translationYBy(3000f).setDuration(5000).withEndAction() {
                        bad2down()
                    }

                }
        }
        fun good1down() {
            good1.animate().translationY(-1000f).translationX(position[2].toFloat()).setDuration(0)
                .withEndAction() {
                    good1.animate().translationYBy(3000f).setDuration(5000).withEndAction() {
                        good1down()
                    }

                }
        }

        fun good2down() {
            good2.animate().translationY(-800f).translationX(position[3].toFloat()).setDuration(0)
                .withEndAction() {
                    good2.animate().translationYBy(3000f).setDuration(5000).withEndAction() {
                        good2down()
                    }

                }
        }
        bad1down()
        bad2down()
        good1down()
        good2down()

        val good1listener = View.OnTouchListener(function = { view, motionEvent ->

            if (motionEvent.action == MotionEvent.ACTION_MOVE) {

                view.y = motionEvent.rawY - view.height/2
                view.x = motionEvent.rawX - view.width/2
            }

            true

        })
        good1.setOnTouchListener(good1listener)

        val good2listener = View.OnTouchListener(function = { view, motionEvent ->

            if (motionEvent.action == MotionEvent.ACTION_MOVE) {

                view.y = motionEvent.rawY - view.height/2
                view.x = motionEvent.rawX - view.width/2
            }

            true

        })
        good2.setOnTouchListener(good2listener)


        val bad1lisener = View.OnTouchListener(function = { view, motionEvent ->

            if (motionEvent.action == MotionEvent.ACTION_MOVE) {

                view.y = motionEvent.rawY - view.height/2
                view.x = motionEvent.rawX - view.width/2
            }

            true

        })
        bad1.setOnTouchListener(bad1lisener)

        val bad2lisener = View.OnTouchListener(function = { view, motionEvent ->

            if (motionEvent.action == MotionEvent.ACTION_MOVE) {

                view.y = motionEvent.rawY - view.height/2
                view.x = motionEvent.rawX - view.width/2
            }

            true

        })
        bad2.setOnTouchListener(bad2lisener)

        livetext.text = "Lives: "+live
        val ScoreGame = Intent(this, Scoremenu::class.java)
        fun EndGame(){
            ScoreGame.putExtra("int", "" + score)
            Handler().postDelayed({
                startActivity(ScoreGame)
                finish()
            },1000)

        }

        val handler = Handler()
        handler.post(object : Runnable {
            override fun run() {
                position.shuffle()
                handler.postDelayed(this::run, 5000)
            }
        })

        handler.post(object : Runnable {
            override fun run() {
                val good1rect = Rect()
                good1.getHitRect(good1rect)

                val good2rect = Rect()
                good2.getHitRect(good2rect)

                val bad1rect = Rect()
                bad1.getHitRect(bad1rect)

                val bad2rect = Rect()
                bad2.getHitRect(bad2rect)

                val goodbuyrect = Rect()
                goodbuy.getHitRect(goodbuyrect)

                val badoutrect = Rect()
                badout.getHitRect(badoutrect)

if (live == 0){
    EndGame()
}

                fun logic() {
                    if (Rect.intersects(good1rect, goodbuyrect)) {
                        good1down()
                        score++
                        intent.putExtra("int", "" + score)
                        scoretext.text = "Score: "+score
                    }
                     if   (Rect.intersects(good2rect, goodbuyrect)){
                         good2down()
                         score++
                         intent.putExtra("int", "" + score)
                         scoretext.text = "Score: "+score
                     }

                    if (Rect.intersects(good1rect, badoutrect)) {
                        good1.animate().translationY(-1000f).translationX(position[2].toFloat()).setDuration(0)
                        good1down()
                        live--
                        livetext.text = "Lives: "+live
                    }
                    if (Rect.intersects(good2rect, badoutrect))
                    {
                        good2down()
                        live--
                        livetext.text = "Lives: "+live
                    }
                    if (Rect.intersects(bad1rect, badoutrect)) {
                        bad1down()
                        score++
                        scoretext.text = "Score: "+score
                        intent.putExtra("int", "" + score)
                    }
                    if   (Rect.intersects(bad2rect, badoutrect)){
                        bad2down()
                        score++
                        scoretext.text = "Score: "+score
                        intent.putExtra("int", "" + score)
                    }

                    if (Rect.intersects(bad1rect, goodbuyrect)) {
                        bad1down()
                        live--
                        livetext.text = "Lives: "+live
                    }
                    if (Rect.intersects(bad2rect, goodbuyrect))
                    {
                        bad2down()
                        live--
                        livetext.text = "Lives: "+live
                    }


                }
                logic()
                handler.postDelayed(this::run, 50)

            }
        })

    }
}