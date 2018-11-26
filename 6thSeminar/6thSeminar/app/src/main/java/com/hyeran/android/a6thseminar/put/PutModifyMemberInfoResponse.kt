package com.hyeran.android.a6thseminar.put

data class PutModifyMemeberInfoResponse (
        val status: String,
        val message: String,
        val data: ModifyMemeberInfoData?
)

data class ModifyMemeberInfoData (
        val u_id: Int,
        val u_name: String,
        val u_part: String,
        val u_profile: String,
        val u_email: String,
        val auth: Boolean
)