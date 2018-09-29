package com.hyeran.android.a1stseminar

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)  // 이전에 있던 상태 저장
        setContentView(R.layout.activity_main)

        setClickListener()
    }

    private fun setClickListener() {
        btn_main_act_part_ok.setOnClickListener {
            var mPartName : String = et_main_act_what_is_your_part.text.toString()
            et_main_act_what_is_your_part.text.clear()
            // anko로 간단하게 toast 구현
            toast(mPartName)
        }
    }
}
