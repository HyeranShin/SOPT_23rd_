package com.hyeran.android.a6thseminar.post

data class PostLikeResponse (
        val status: Int,
        val message: String,
        val data: LikeData?
)

data class LikeData (
        val b_id: Int,
        val b_title: String,
        val b_contents: String,
        val b_date: String,
        val u_id: Int,
        val b_like: Int,
        val b_photo: String,
        val auth: Boolean,
        var like: Boolean
)