package com.example.medieval_clicker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout

var counter = 0;

var counterView: TextView? = null
var backgroundView: ConstraintLayout? = null

class GameActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        counterView = findViewById(R.id.count)
        backgroundView = findViewById(R.id.cLayout)
    }

    override fun onPause() {
        super.onPause()
        counter = 0;
    }

    fun moveToStartActivity(view: View) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent);
    }

    fun incrimentCount(view: View) {
        counter++
        counterView?.setText(counter.toString())
    }
}