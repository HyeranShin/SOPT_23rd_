package com.hyeran.android.a4thseminar.db

import android.content.Context
import android.content.SharedPreferences

// object: JAVA로 치면 static 객체를 만들 때 사용
object SharedPreferenceController {

    // DB의 테이블 이름 같은 개념
    private val USER_NAME : String = "user_name"

    private val USER_ID : String = "user_id"
    private val USER_PW : String = "user_pw"

    // ID 집어넣기
    fun setUserID(ctx : Context, input_id : String) {
        // 1. getSharedPrefence를 통해 앱에 존재하는 단일 인스턴스를 가져온다.
        val preferences : SharedPreferences = ctx.getSharedPreferences(USER_NAME, Context.MODE_PRIVATE)
        // 2. editor를 연다.
        val editor : SharedPreferences.Editor = preferences.edit()
        // 3. 값을 넣는다.
        editor.putString(USER_ID, input_id)
        // 4. commit을 통해 마침을 알린다.
        editor.commit()
    }

    // PW 집어넣기
    fun setUserPW(ctx : Context, input_pw : String) {
        val preferences : SharedPreferences = ctx.getSharedPreferences(USER_NAME, Context.MODE_PRIVATE)
        val editor : SharedPreferences.Editor = preferences.edit()
        editor.putString(USER_PW, input_pw)
        editor.commit()
    }

    // ID 꺼내기
    fun getUserID(ctx : Context) : String {
        // 1. getSharedPreference를 통해 앱에 존재하는 단일 인스턴스를 가져온다.
        val preferences : SharedPreferences = ctx.getSharedPreferences(USER_NAME, Context.MODE_PRIVATE)
        // Context.MODE_PRIVATE을 mode에 써주면, 해당 어플리케이션 외 다른 곳에서는 이 데이터를 접근 할 수 없다.
        // 2. key를 통해 꺼낸다.
        return preferences.getString(USER_ID, "")   // (key 명, 든 게 없을 때 리턴할 값)
    }

    // PW 꺼내기
    fun getUserPW(ctx : Context) : String {
        val preferences : SharedPreferences = ctx.getSharedPreferences(USER_NAME, Context.MODE_PRIVATE)
        return preferences.getString(USER_PW, "")
    }

    // "user_name"에 저장된 모든 데이터 제거
    fun clearUserSharedPreferences(ctx : Context) {
        val preferences : SharedPreferences = ctx.getSharedPreferences(USER_NAME, Context.MODE_PRIVATE)
        val editor : SharedPreferences.Editor = preferences.edit()
        editor.clear()
        editor.commit()
    }
}