package com.coingen.transitions

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnticipateOvershootInterpolator
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.transition.ChangeBounds
import androidx.transition.TransitionInflater
import androidx.transition.TransitionManager

class ConstraintsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_start)

        val constraintsContainer: ConstraintLayout = findViewById(R.id.constraint_container)

        var isDetailsShown = false


        fun showDetails() {
            isDetailsShown = true

            val constraints = ConstraintSet().apply {
                clone(this@ConstraintsActivity, R.layout.layout_end)
            }

            ChangeBounds().apply {
                interpolator = AnticipateOvershootInterpolator(1.0f).apply {
                    duration = 2000L
                }
            }.also {
                TransitionManager.beginDelayedTransition(constraintsContainer, it)
            }

            constraints.applyTo(constraintsContainer)
        }

        fun hideDetails() {
            isDetailsShown = false


            val constraints = ConstraintSet().apply {
                clone(this@ConstraintsActivity, R.layout.layout_start)
            }

            ChangeBounds().apply {
                interpolator = AnticipateOvershootInterpolator(1.0f).apply {
                    duration = 2000L
                }
            }.also {
                TransitionManager.beginDelayedTransition(constraintsContainer, it)
            }

            constraints.applyTo(constraintsContainer)
        }

        findViewById<View>(R.id.backgroundImage).setOnClickListener {
            if (isDetailsShown) {
                hideDetails()
            } else {
                showDetails()

            }
        }

    }
}