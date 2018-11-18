package com.hyeran.android.a5thseminar

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import kotlinx.android.synthetic.main.activity_sub.*
import org.jetbrains.anko.toast

class SubActivity : AppCompatActivity() {

    // lazy: 사용하지 않으면 메모리에 올라가지 않는다. (효율적인 메모리 사용)
    val bottomBarAnimation : Animation by lazy {
        AnimationUtils.loadAnimation(this, R.anim.example_animation)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sub)

        bottomBarAnimation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationRepeat(animation: Animation?) {
                toast("애니메이션 다시 시작!")
            }

            override fun onAnimationEnd(animation: Animation?) {
                toast("애니메이션 끝!")
            }

            override fun onAnimationStart(animation: Animation?) {
                toast("애니메이션 시작!")
            }
        })

        btn_sub_act_show_bottom_bar.setOnClickListener {
            rl_sub_act_bottom_bar.visibility = View.VISIBLE
            rl_sub_act_bottom_bar.startAnimation(bottomBarAnimation)
        }
    }
}
