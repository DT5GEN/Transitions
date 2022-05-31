package com.coingen.transitions

import android.graphics.Rect
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.*

class TransitionActivity : AppCompatActivity() {

    private val recycler: RecyclerView by lazy {
        findViewById(R.id.recycler)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transition)

        val root: LinearLayout = findViewById(R.id.root)
        val textHello: TextView = findViewById(R.id.hello_text)
        val animate: Button = findViewById(R.id.animate)

        var isTextVisible = false

        animate.setOnClickListener {
            isTextVisible = isTextVisible.not()
            TransitionManager.beginDelayedTransition(root, Slide(Gravity.END))
            // textHello.visibility = View.VISIBLE
            textHello.visibility = if (isTextVisible) View.VISIBLE else View.GONE
        }

        val recycler: RecyclerView = findViewById(R.id.recycler)

        recycler.adapter = GridAdapter()

    }

    private fun explode(view: View) {

        val rect = Rect()
        view.getGlobalVisibleRect(rect)

        val explode = Explode().apply {
            epicenterCallback = object : Transition.EpicenterCallback(){
                override fun onGetEpicenter(transition: Transition): Rect = rect
            }

            duration = 400L
        }

        val animSet = TransitionSet()
            .addTransition(explode)
            .addTransition(Fade().addTarget(view))

        TransitionManager.beginDelayedTransition(recycler, animSet)
       view.visibility = View.GONE
    }


    inner class GridAdapter : RecyclerView.Adapter<GridAdapter.ButtonViewHolder>() {


        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ButtonViewHolder =
            ButtonViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.item_buttom, parent, false)
            )

        override fun onBindViewHolder(holder: ButtonViewHolder, position: Int) {

        }

        override fun getItemCount(): Int = 35

        inner class ButtonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

            private val btn: Button = itemView.findViewById(R.id.btn)

            init {
                btn.setOnClickListener {
                    explode(itemView)
                }
            }
        }
    }
}