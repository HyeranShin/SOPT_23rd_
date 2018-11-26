package com.hyeran.android.a6thseminar.put

data class PutModifyBoardResponse (
        val status: String,
        val message: String,
        val data: ModifyBoardDate?
)

data class ModifyBoardDate (
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