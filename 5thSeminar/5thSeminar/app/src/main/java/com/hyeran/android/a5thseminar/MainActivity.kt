package com.hyeran.android.a5thseminar

import android.animation.Animator
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.airbnb.lottie.LottieAnimationView
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val confeti_animation : LottieAnimationView = findViewById(R.id.lottie_main)
        // 코틀린에서는 뷰가 바로 연결되지만 findViewById를 연결하는 이유는 형을 명시해주기 위해서

        lottie_main.setOnClickListener {
            confeti_animation.playAnimation()
        }

        // Ctrl+Alt+L: 들여쓰기

        // Ctrl+Space: 파라미터 추천
        confeti_animation.addAnimatorListener(object : Animator.AnimatorListener{

            // Ctrl+I, Alt+Enter: 필수로 구현해야 하는 함수
            override fun onAnimationRepeat(animation: Animator?) {
            }

            override fun onAnimationEnd(animation: Animator?) {
                startActivity<SubActivity>()
            }

            override fun onAnimationCancel(animation: Animator?) {
            }

            override fun onAnimationStart(animation: Animator?) {
            }
        })
    }
}
