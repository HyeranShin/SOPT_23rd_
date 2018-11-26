package com.hyeran.android.a6thseminar.get

data class GetBoardDetailResponse (
        val status: Int,
        val message: String,
        val data: BoardDetailData?
)

data class BoardDetailData (
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