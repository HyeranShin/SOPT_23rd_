package com.hyeran.android.a6thseminar.post

/*
중요!!! 응답 데이터와 같은 데이터 명, 데이터 타입을 써야한다.
 */
data class PostSignUpResponse (
        // 상수 val, 변수 var
        val status: Int,
        val message: String
        // null로 주는 데이터는 안받아도 된다.
)