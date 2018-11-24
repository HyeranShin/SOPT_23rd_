package com.hyeran.android.a6thseminar.get

import com.hyeran.android.a6thseminar.data.BoardData

data class GetBoardListResponse (
        val status: Int,
        val message: String,
        val data: ArrayList<BoardData>
)