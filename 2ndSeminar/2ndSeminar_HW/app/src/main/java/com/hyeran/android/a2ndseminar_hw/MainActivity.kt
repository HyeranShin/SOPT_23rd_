package com.hyeran.android.a2ndseminar_hw

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivityForResult
import org.jetbrains.anko.toast

class MainActivity : AppCompatActivity() {

    val REQUEST_CODE_SIGNUP_ACTIVITY = 999

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = "혜란이의 2차 세미나 과제 <MainActivity>"

        setClickListener()
    }

    private fun setClickListener() {

        btn_main_signin.setOnClickListener {
            val id = et_main_id.text.toString()
            if(id == "") {
                toast("ID 값을 입력하지 않으셨습니다!")
            } else {
                toast("로그인 버튼을 누르셨습니다!")
            }
        }

        btn_main_signup.setOnClickListener {
            val id = et_main_id.text.toString()
            if(id == "") {
                toast("ID 값을 입력하지 않으셨습니다!")
            } else {
                toast("회원가입 화면으로 넘어갑니다!")
                startActivityForResult<SignUpActivity>(REQUEST_CODE_SIGNUP_ACTIVITY, "mainID" to id)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == REQUEST_CODE_SIGNUP_ACTIVITY) {
            if(resultCode == Activity.RESULT_OK) {
                toast("회원가입이 완료되었습니다!")
                val receivedID = data!!.getStringExtra("signupID")
                et_main_id.setText(receivedID)
                // 커서를 텍스트의 끝으로 이동
                et_main_id.setSelection(receivedID.length)
            }
            else if (resultCode == Activity.RESULT_CANCELED) {
                toast("Close 버튼을 눌렀으므로 회원가입이 완료되지 않았습니다.")
            }
        }
    }
}
