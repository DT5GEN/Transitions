package com.coingen.transitions

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.core.view.updateLayoutParams
import androidx.transition.ChangeBounds
import androidx.transition.ChangeImageTransform
import androidx.transition.TransitionManager
import androidx.transition.TransitionSet

class ImageAnimationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_animation)

        var isFullScreen = false

        val img: ImageView = findViewById(R.id.image)
        val root: FrameLayout = findViewById(R.id.root)



        img.setOnClickListener {

            isFullScreen = isFullScreen.not()

            TransitionManager.beginDelayedTransition(root, TransitionSet()
                .addTransition(ChangeBounds())
                .addTransition(ChangeImageTransform())
            )

//            val dimension = if (isFullScreen) ViewGroup.LayoutParams.MATCH_PARENT
//            else ViewGroup.LayoutParams.WRAP_CONTENT
//

            val dimensionWidth = if (isFullScreen) ViewGroup.LayoutParams.MATCH_PARENT
            else resources.getDimension(R.dimen.img_width).toInt()


            val dimensionHeight = if (isFullScreen) ViewGroup.LayoutParams.MATCH_PARENT
            else ViewGroup.LayoutParams.WRAP_CONTENT



            val scaleType = if (isFullScreen) ImageView.ScaleType.CENTER_CROP
            else ImageView.ScaleType.FIT_CENTER


            img.updateLayoutParams {
                width = dimensionWidth
                height = dimensionHeight
            }

            img.scaleType = scaleType
        }
    }







}