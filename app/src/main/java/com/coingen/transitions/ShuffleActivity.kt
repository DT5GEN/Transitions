package com.coingen.transitions

import android.os.Bundle
import android.view.Gravity
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.transition.ChangeBounds
import androidx.transition.TransitionManager

class ShuffleActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shuffle)

        val container: LinearLayout = findViewById(R.id.container)

        val items = (0..10).map {
            "Item number $it"
        }.toMutableList()

        fun redrawList() {

            container.removeAllViews()

            items.forEach {
                val textView = TextView(this).apply {
                    text = it
                    gravity = Gravity.CENTER_HORIZONTAL
                    ViewCompat.setTransitionName(this, it)
                }.also {
                    container.addView(it)
                }
            }

        }

        findViewById<Button>(R.id.shuffle).setOnClickListener {

            TransitionManager.beginDelayedTransition(container, ChangeBounds())
            items.shuffle()
            redrawList()
        }
        redrawList()
    }
}