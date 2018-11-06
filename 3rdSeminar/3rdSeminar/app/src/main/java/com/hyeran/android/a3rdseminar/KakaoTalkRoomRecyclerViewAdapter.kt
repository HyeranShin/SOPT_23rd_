package com.hyeran.android.a3rdseminar

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

/*
RecyclerView를 위한 준비물 순서

1. Data Class -> Item에 들어갈 Data들

2. 반복적인 View UI를 구성할 xml 파일 -> Item UI

3. View Holder Class
    -> ViewHolder pattern:findViewById() 호출에 대한 비용을 줄여줍니다.
    -> 들어가는 로직은, ViewHolder를 상속 받아서 Item UI에 있는 View들의 ID를 할당입니다.

4. Recycler View Adapter Class -> View와 Data를 붙여주는 용도
 */
class KakaoTalkRoomRecyclerViewAdapter(val ctx : Context, val dataList : ArrayList<KakaoTalkRoomData>) : RecyclerView.Adapter<KakaoTalkRoomRecyclerViewAdapter.Holder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        // 뷰 인플레이트
        val view : View = LayoutInflater.from(ctx).inflate(R.layout.rv_item_kakao_talk_room, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int = dataList.size

    // Holder에서 RelativeLayout의 ID 잡아주고,
    // onBindViewHolder에서 리스너 달아주기
    override fun onBindViewHolder(holder: Holder, position: Int) {
        // 뷰 바인딩
        holder.title.text = dataList[position].title
        holder.content.text = dataList[position].content
        holder.person_cnt.text = dataList[position].person_cnt.toString()
        holder.time.text = dataList[position].time

        holder.item_btn.setOnClickListener {
            ctx.toast("메인 액티비티로~")
            ctx.startActivity<MainActivity>()
        }
    }

    inner class Holder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val title : TextView = itemView.findViewById(R.id.tv_rv_item_kakao_talk_room_title) as TextView
        val content : TextView = itemView.findViewById(R.id.tv_rv_item_kakao_talk_room_room_content) as TextView
        val person_cnt : TextView = itemView.findViewById(R.id.tv_rv_item_kakao_talk_room_person_cnt) as TextView
        val time : TextView = itemView.findViewById(R.id.tv_rv_item_kakao_talk_room_time) as TextView

        val item_btn : RelativeLayout = itemView.findViewById(R.id.btn_rv_item_kakao_talk_room) as RelativeLayout
    }
}