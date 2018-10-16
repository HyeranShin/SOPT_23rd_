package com.hyeran.android.a2ndseminar_hw

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_sign_up.*
import org.jetbrains.anko.toast

class SignUpActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        title = "혜란이의 2차 세미나 과제 <SignUpActivity>"

        // id 값 꺼내기
        val receivedId = intent.getStringExtra("mainID")
        // String 값을 EditText 텍스트로 설정하기
        et_signup_id.setText(receivedId)
        // 커서를 텍스트의 끝으로 이동
        et_signup_id.setSelection(et_signup_id.length())
        setClickListener()
    }

    private fun setClickListener() {
        btn_signup_complete.setOnClickListener {
            val id = et_signup_id.text.toString()
            if(id == "") {
                toast("ID 값을 입력하지 않으셨습니다!")
            } else {
                val intent = Intent()
                intent.putExtra("signupID", id)
                setResult(Activity.RESULT_OK, intent)
                finish()
            }

        }

        btn_signup_close.setOnClickListener {
            setResult(Activity.RESULT_CANCELED)
            finish()
        }
    }
}
