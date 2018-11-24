package com.hyeran.android.a6thseminar.network

import android.app.Application
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// 이해하고 외울 필요 없다
// 앱잼 때 복붙해서 baseURL만 바꾸면 됨
class ApplicationController : Application() {
    private val baseURL = "http://bghgu.tk:8080"
    lateinit var networkService: NetworkService

    // 싱글톤 패턴 만들 때
    // 이 인스턴스를 하나만 만들겠다
    companion object {
        lateinit var instance: ApplicationController
    }

    override fun onCreate() {
        super.onCreate()
        instance = this // 싱글톤으로 만든 인스턴스 static 변수에 본인이 들어간다
        buildNetwork()
    }

    private fun buildNetwork() {
        val retrofit: Retrofit = Retrofit.Builder()
                .baseUrl(baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()    // 인스턴스 하나가 retrofit에 들어감
        networkService = retrofit.create(NetworkService::class.java)
    }
}