package com.hyeran.android.a6thseminar.post

// 꺽쇠 하나 당 data class 하나
data class PostLogInResponse (
        val status: Int,
        val message: String,
        val data: LogInData
)

data class LogInData (
        val token: String
)