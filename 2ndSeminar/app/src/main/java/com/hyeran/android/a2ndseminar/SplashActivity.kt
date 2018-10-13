package com.hyeran.android.a2ndseminar

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import org.jetbrains.anko.startActivity

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        Handler().apply {
            // Ctrl+P -> 안에 들어갈 매개변수가 뭔지 알려준다.
            postDelayed({

                // 기본
                val intent : Intent = Intent(this@SplashActivity, MainActivity::class.java)
                intent.putExtra("data1", "헬로우")
                intent.putExtra("data2", "월드")
                intent.putExtra("data3", 1000)
                startActivity(intent)   // Ctrl+Shift+Space: 자동으로 채워주기

                // anko 라이브러리
//                startActivity<MainActivity>("data1" to "헬로우", "data2" to "월드", "data3" to 1000)

                finish()

            }, 2000)
        }
    }
}
