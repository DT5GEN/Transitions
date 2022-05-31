package com.coingen.transitions

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

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
                .setDuration(300L)
                .setListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animation: Animator?) {
                        super.onAnimationEnd(animation)
                        blur.visibility = View.GONE
                        blur.setOnClickListener(null)
                    }
                })
            ObjectAnimator.ofFloat(icon, "rotation", -0f).start()

            option1.apply{
                alpha = 1.0f
                translationY = 0f
                visibility = View.VISIBLE
            }
            option1.animate()
                .alpha(0.0f)
                .translationY(menuItemHeight)
                .setDuration(150L)
                .setListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animation: Animator?) {
                        super.onAnimationEnd(animation)
                        option1.visibility = View.GONE


                        option2.apply {
                            alpha = 1.0f
                            translationY = 0f
                            visibility = View.VISIBLE
                        }

                        option2.animate()
                            .alpha(0.0f)
                            .translationY(2 * menuItemHeight + menuItemMargin)
                            .setDuration(150L)
                            .setListener(object : AnimatorListenerAdapter() {
                                override fun onAnimationEnd(animation: Animator?) {
                                    super.onAnimationEnd(animation)
                                    option2.visibility = View.GONE

                                    option3.apply{
                                        alpha = 1.0f
                                        translationY = 0.0f
                                        visibility = View.VISIBLE
                                    }


                                    option3.animate()
                                        .alpha(0.0f)
                                        .translationY( 3 * menuItemHeight + 2* menuItemMargin)
                                        .setDuration(150L)
                                        .setListener(object : AnimatorListenerAdapter() {
                                            override fun onAnimationEnd(animation: Animator?) {
                                                super.onAnimationEnd(animation)
                                                option3.visibility = View.GONE
                                            }}).start()


                                }
                            }).start()


//                        blur.setOnClickListener {
//                            closeMenu()
//                        }

                    }})
                .start()

        }

        fun openMenu() {
            isMenuOpened = true
            blur.visibility = View.VISIBLE
            blur.alpha = 0.0f

            blur.animate()
                .alpha(0.9f)
                .setDuration(500L)
                .setListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animation: Animator?) {
                        super.onAnimationEnd(animation)

                        // blur.visibility = View.VISIBLE

                        blur.setOnClickListener {
                            closeMenu()
                        }
                    }
                })
            ObjectAnimator.ofFloat(icon, "rotation", 135f).setDuration(750).start()

            option3.apply{
                alpha = 0.0f
                translationY = menuItemHeight
                visibility = View.VISIBLE
            }
            option3.animate()
                .alpha(1.0f)
                .translationY(0f)
                .setDuration(100L)
                .setListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animation: Animator?) {
                        super.onAnimationEnd(animation)

                        option2.apply {
                            alpha = 0.0f
                            translationY = 2 * menuItemHeight + menuItemMargin
                            visibility = View.VISIBLE
                        }

                        option2.animate()
                            .alpha(1.0f)
                            .translationY(0f)
                            .setDuration(200L)
                            .setListener(object : AnimatorListenerAdapter() {
                                override fun onAnimationEnd(animation: Animator?) {
                                    super.onAnimationEnd(animation)

                                    option1.apply{
                                        alpha = 0.0f
                                        translationY = 3 * menuItemHeight + 2* menuItemMargin
                                        visibility = View.VISIBLE
                                    }


                                    option1.animate()
                                        .alpha(1.0f)
                                        .translationY(0f)
                                        .setDuration(250L)
                                        .setListener(object : AnimatorListenerAdapter() {
                                            override fun onAnimationEnd(animation: Animator?) {
                                                super.onAnimationEnd(animation)
                                            }}).start()


                                            }
                            }).start()


//                        blur.setOnClickListener {
//                            closeMenu()
//                        }

                    }})
                .start()
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