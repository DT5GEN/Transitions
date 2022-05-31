package com.coingen.transitions

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView

class FabAnimationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fab_animation)

        var isMenuOpened = false

        val fab: FrameLayout = findViewById(R.id.fab)

        val icon: ImageView = findViewById(R.id.plus_icon)

        val option1: TextView = findViewById(R.id.option1)
        val option2: TextView = findViewById(R.id.option2)
        val option3: TextView = findViewById(R.id.option3)

        val blur: FrameLayout = findViewById(R.id.blur)


        val menuItemHeight = resources.getDimension(R.dimen.menu_height)
        val menuItemMargin = resources.getDimension(R.dimen.margin_menu)


        fun closeMenu() {

            isMenuOpened =false

            blur.visibility = View.VISIBLE
            blur.alpha = 1.0f

            blur.animate()
                .alpha(0.0f)
                .setDuration(200L)
                .setListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animation: Animator?) {
                        super.onAnimationEnd(animation)
                        blur.visibility = View.GONE
                        blur.setOnClickListener(null)
                    }
                })
            ObjectAnimator.ofFloat(icon, "rotation", -0f).start()
        }

        fun openMenu() {
            isMenuOpened = true
            blur.visibility = View.VISIBLE
            blur.alpha = 0.0f

            blur.animate()
                .alpha(0.9f)
                .setDuration(800L)
                .setListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animation: Animator?) {
                        super.onAnimationEnd(animation)

                        // blur.visibility = View.VISIBLE

                        blur.setOnClickListener {
                            closeMenu()
                        }
                    }
                })
            ObjectAnimator.ofFloat(icon, "rotation", 135f).start()
        }


        fab.setOnClickListener {

            if (isMenuOpened) {
                closeMenu()
            } else {
                openMenu()
            }

        }


    }
}