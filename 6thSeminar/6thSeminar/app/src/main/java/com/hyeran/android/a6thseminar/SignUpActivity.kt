package com.hyeran.android.a6thseminar

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.hyeran.android.a6thseminar.network.ApplicationController
import com.hyeran.android.a6thseminar.network.NetworkService
import com.hyeran.android.a6thseminar.post.PostSignUpResponse
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_sign_up.*
import org.jetbrains.anko.toast
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpActivity : AppCompatActivity() {

    val networkService: NetworkService by lazy {    // 호출할떄만
        ApplicationController.instance.networkService
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        setOnBtnClickListener()
    }

    private fun setOnBtnClickListener() {
        // 회원가입 버튼을 눌렀을 때 회원가입에 대한 통신이 이루어진다.
        btn_sign_up_act_complete.setOnClickListener {
            getSignUpResponse()
        }
        btn_sign_up_act_close.setOnClickListener {
            finish()
        }
    }

    private fun getSignUpResponse() {
        // EditText에 있는 값 받기
        // 앱잼에서는 text 값이 비어있는지 체크하는 코드 필요!
        val input_name: String = et_sign_up_act_name.text.toString()
        val input_pw: String = et_sign_up_act_pw.text.toString()
        val input_email: String = et_sign_up_act_email.text.toString()
        val input_part: String = et_sign_up_act_part.text.toString()

        // Json 형식의 객체 만들기
        var jsonObject = JSONObject()
        jsonObject.put("name", input_name)
        jsonObject.put("email", input_email)
        jsonObject.put("password", input_pw)
        jsonObject.put("part", input_part)

        // Gson 라이브러리의 Json Parser를 통해 객체를 Json으로!
        // gsonObject: 진짜 JSONObject로 파싱한 거 (요청 바디 {}가 하나의 JSONObject)
        val gsonObject = JsonParser().parse(jsonObject.toString()) as JsonObject

        // 추상메소드를 retrofit 형식으로 통신 시작 (통신은 무조건 비동기)
        // 안드로이드의 Main Thread는 UI만 관장(여기서 통신하면 앱이 터짐), 비동기로 Thread를 하나 파서 통신
        val postSignUpResponse: Call<PostSignUpResponse> =  // Call이 여러갠데 retrofit의 Call 사용해야 함
                networkService.postSignUpResponse("application/json", gsonObject)

        // object: -> 인수로 함수가 들어갈 때 사용 뒤에 응답으로 받을 data class
        postSignUpResponse.enqueue(object: Callback<PostSignUpResponse>{
            override fun onFailure(call: Call<PostSignUpResponse>?, t: Throwable?) {
                Log.e("<회원-회원 가입> 통신 fail", t.toString())   // fail 이유 Log 찍기
            }
            // 통신 성공 시 수행되는 메소드
            // response로 응답 메세지 받음
            override fun onResponse(call: Call<PostSignUpResponse>?, response: Response<PostSignUpResponse>) {
                if (response.isSuccessful) {
                    toast(response.body()!!.message)
                    finish()
                    // 서버 통신 후 성공했다면, 이 onResponse 메소드 블락 안에서 데이터를 꺼내오고,
                    // View에 뿌려지는 작업을 넣는다.
                } else {
                    Log.e("<회원-회원 가입> 응답 fail: ", response.code().toString())
                    Log.e("<회원-회원 가입> 응답 fail: ", response.errorBody().toString())
                }
            }
        })
    }
}