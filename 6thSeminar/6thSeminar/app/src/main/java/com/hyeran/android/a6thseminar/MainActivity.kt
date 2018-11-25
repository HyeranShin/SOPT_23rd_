package com.hyeran.android.a6thseminar

import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.hyeran.android.a6thseminar.db.SharedPreferencesController
import com.hyeran.android.a6thseminar.network.ApplicationController
import com.hyeran.android.a6thseminar.network.NetworkService
import com.hyeran.android.a6thseminar.post.PostLogInResponse
import kotlinx.android.synthetic.main.activity_board.*
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    // 이 부분은 통신이 들어가는 class에는 무조건 넣기
    val networkService: NetworkService by lazy {
        ApplicationController.instance.networkService
    }

    // onCreate 같은 생명주기 관련 함수에서는 너무 많은 코드 X. 함수로 빼내는거 권장
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setOnBtnClickListener()
        // 자동 로그인
        if (SharedPreferencesController.getAuthorization(this).isNotEmpty()) {
            startActivity<BoardActivity>()
        }
    }

    private fun setOnBtnClickListener() {
        btn_main_act_log_in.setOnClickListener {
            getLoginResponse()
        }

        btn_main_act_sign_up.setOnClickListener {
            startActivity<SignUpActivity>()
        }
    }

    private fun getLoginResponse() {
        if (et_main_act_email.text.toString().isNotEmpty() && et_main_act_pw.text.toString().isNotEmpty()) {
            val input_email = et_main_act_email.text.toString()
            val input_pw = et_main_act_pw.text.toString()
            val jsonObject: JSONObject = JSONObject()
            jsonObject.put("email", input_email)
            jsonObject.put("password", input_pw)
            val gsonObejct = JsonParser().parse(jsonObject.toString()) as JsonObject

            val postLogInResponse = networkService.postLogInResponse("application/json", gsonObejct)
            postLogInResponse.enqueue(object: Callback<PostLogInResponse>{
                override fun onFailure(call: Call<PostLogInResponse>?, t: Throwable?) {
                    Log.e("<로그인> 통신 fail", t.toString())
                }

                override fun onResponse(call: Call<PostLogInResponse>?, response: Response<PostLogInResponse>) {
                    if (response.isSuccessful) {
                        toast(response.body()!!.message)

                        val token = response.body()!!.data.token
                        // SharedPreferences에 토큰 저장
                        SharedPreferencesController.setAuthorization(this@MainActivity, token)
                        toast(SharedPreferencesController.getAuthorization(this@MainActivity))
                        startActivity<BoardActivity>()
                    } else {
                        Log.e("<로그인> 응답 Fail: ", response.code().toString())
                        Log.e("<로그인> 응답 Fail: ", response.errorBody().toString())
                    }
                }
            })
        }
    }
}