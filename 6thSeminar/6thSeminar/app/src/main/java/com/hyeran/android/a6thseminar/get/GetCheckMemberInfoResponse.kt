package com.hyeran.android.a6thseminar.get

data class GetCheckMemberInfoResponse(
        val status: Int,
        val message: String,
        val date: CheckMemberInfoData?
)

data class CheckMemberInfoData(
        val u_id: Int,
        val u_name: String,
        val u_part: String,
        val u_profile: String,
        val u_email: String,
        val auth: Boolean
)
