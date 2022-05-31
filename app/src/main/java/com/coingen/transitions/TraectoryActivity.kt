package com.coingen.transitions

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.transition.ChangeBounds
import android.transition.TransitionManager
import android.view.Gravity
import android.widget.Button
import android.widget.FrameLayout
import androidx.core.view.updateLayoutParams
import androidx.transition.ArcMotion

class TraectoryActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_traectory)

        val root : FrameLayout = findViewById(R.id.root)
        val animate: Button = findViewById(R.id.btn_traectory)
        var isMoved = false

        animate.setOnClickListener {

            isMoved = isMoved.not()

            ChangeBounds()
                .apply {

            setPathMotion( ArcMotion().apply {
                this.maximumAngle = 85.0F
            })
                duration = 4000L
            }
                .also {
                    androidx.transition.TransitionManager.beginDelayedTransition(root, it)
                }
            animate.updateLayoutParams<FrameLayout.LayoutParams>{
                gravity =
                    if (isMoved) Gravity.END or Gravity.BOTTOM else Gravity.TOP or Gravity.START
            }
        }

    }
}