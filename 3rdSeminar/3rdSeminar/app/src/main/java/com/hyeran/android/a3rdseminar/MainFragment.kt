package com.hyeran.android.a3rdseminar


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment : Fragment() {

    lateinit var kakaoTalkRoomRecyclerViewAdapter: KakaoTalkRoomRecyclerViewAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setRecyclerView()
    }

    private fun setRecyclerView() {
        var dataList : ArrayList<KakaoTalkRoomData> = ArrayList()
        dataList.add(KakaoTalkRoomData("[DoIT_SOPT] 안드로이드파트", "윤환팟짱님", 53, "오후 6:53"))
        dataList.add(KakaoTalkRoomData("[DoIT_SOPT] iOS파트", "씅수오빠", 36, "오후 5:53"))
        dataList.add(KakaoTalkRoomData("[DoIT_SOPT] 서버파트", "배다슬기님", 55, "오후 4:53"))
        dataList.add(KakaoTalkRoomData("[DoIT_SOPT] 기획파트", "마피아42", 42, "오후 3:53"))
        dataList.add(KakaoTalkRoomData("[DoIT_SOPT] 디자인파트", "디쟌디쟌", 39, "오후 2:53"))
        dataList.add(KakaoTalkRoomData("SOPT 23기 솝풍 12조", "혀기 시끄러", 9, "오후 1:53"))
        dataList.add(KakaoTalkRoomData("SOPT 23기 MT 12조", "(죽은 단톡방)", 13, "오후 12:53"))
        dataList.add(KakaoTalkRoomData("SOPT 23기 버디버디 16조", "생일 추카", 7, "오후 12:03"))

        kakaoTalkRoomRecyclerViewAdapter = KakaoTalkRoomRecyclerViewAdapter(activity!!, dataList)
        rv_main_frag_kakao_talk_room_list.adapter = kakaoTalkRoomRecyclerViewAdapter
        rv_main_frag_kakao_talk_room_list.layoutManager = LinearLayoutManager(activity)
    }
}
