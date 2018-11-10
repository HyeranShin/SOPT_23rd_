package com.hyeran.android.a4thseminar

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.hyeran.android.a4thseminar.adapter.MemoRecyclerViewAdapter
import com.hyeran.android.a4thseminar.data.MemoData
import com.hyeran.android.a4thseminar.db.SharedPreferenceController
import kotlinx.android.synthetic.main.activity_memo.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

class MemoActivity : AppCompatActivity() {

    lateinit var recyclerViewAdapter: MemoRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_memo)

        setOnBtnClickListener()

        setRecyclerView()

        btn_memo_act_add_memo.setOnClickListener {
            addItem()
        }

        // 당겨서 새로 고침 기능
        refresh_memo_act.setOnRefreshListener {
            toast("새로 고침!")
            // 여기서 서버 재통신 로직 구현
            refresh_memo_act.isRefreshing = false // 뺑뺑이 멈추기
        }
    }

    private fun addItem() {
        if(et_memo_act_title.text.toString().isNotEmpty() && et_memo_act_content.text.toString().isNotEmpty()) {
            val position = recyclerViewAdapter.itemCount
            recyclerViewAdapter.dataList.add(MemoData(et_memo_act_title.text.toString(), et_memo_act_content.text.toString()))
            recyclerViewAdapter.notifyItemInserted(position)
        }
    }

    private fun setRecyclerView() {
        var dataList : ArrayList<MemoData> = ArrayList()
        dataList.add(MemoData("안녕", "안녕"))
        dataList.add(MemoData("도희언니", "바보다!"))

        recyclerViewAdapter = MemoRecyclerViewAdapter(this, dataList)
        rv_memo_act_memo_list.adapter = recyclerViewAdapter
        rv_memo_act_memo_list.layoutManager = LinearLayoutManager(this)
    }

    private fun setOnBtnClickListener() {
        btn_memo_act_logout.setOnClickListener {
            // 로그인된 정보 데이터 제거하고 다시 로그인하도록
            SharedPreferenceController.clearUserSharedPreferences(this)
            startActivity<LoginActivity>()
            finish()
        }
    }
}
