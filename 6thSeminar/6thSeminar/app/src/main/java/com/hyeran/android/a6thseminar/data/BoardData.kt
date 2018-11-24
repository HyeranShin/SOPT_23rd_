package com.hyeran.android.a6thseminar.data

// JSON에서 "[]"는 Array라고 생각

// Board(게시물) item에 들어갈 데이터들
class BoardData (
    val b_id: Int,
    val b_title: String,
    val b_contents: String,
    val b_date: String,
    val u_id: Int,
    val b_like: Int,
    val b_photo: String,
    val auth: Boolean,
    val like: Boolean
)