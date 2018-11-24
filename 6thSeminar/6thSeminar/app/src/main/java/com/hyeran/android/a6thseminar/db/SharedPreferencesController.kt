package com.hyeran.android.a6thseminar.db

import android.content.Context

object SharedPreferencesController {
    private val USER_NAME = "MYKEY"
    private val myAuth = "myAuth"

    // authorization = token

    // 저장
    fun setAuthorization(context: Context, authorization: String) {
        // MODE_PRIVATE: 현재 내 기기에서만 볼 수 있는 데이터
        val pref = context.getSharedPreferences(USER_NAME, Context.MODE_PRIVATE)
        val editor = pref.edit()
        editor.putString(myAuth, authorization)
        editor.commit()
    }

    // 불러오기
    fun getAuthorization(context: Context) : String {
        val pref = context.getSharedPreferences(USER_NAME, Context.MODE_PRIVATE) //현재 내 기기에서만 볼수 있는 데이터
        return pref.getString(myAuth, "")
    }

    fun clearSPC(context: Context){
        val pref = context.getSharedPreferences(USER_NAME, Context.MODE_PRIVATE) //현재 내 기기에서만 볼수 있는 데이터
        val editor = pref.edit()
        editor.clear()
        editor.commit()
    }
}