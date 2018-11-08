package com.hyeran.android.a3rdseminar_hw

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var recyclerViewAdapter: RecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setRecycelrView()
    }

    private fun setRecycelrView() {
        var dataList : ArrayList<MyItemData> = ArrayList()
        dataList.add(MyItemData(1, false))
        dataList.add(MyItemData(2, false))
        dataList.add(MyItemData(3, false))
        dataList.add(MyItemData(4, false))
        dataList.add(MyItemData(5, false))
        dataList.add(MyItemData(6, false))
        dataList.add(MyItemData(7, false))
        dataList.add(MyItemData(8, false))
        dataList.add(MyItemData(9, false))
        dataList.add(MyItemData(10, false))
        dataList.add(MyItemData(11, false))
        dataList.add(MyItemData(12, false))
        dataList.add(MyItemData(13, false))
        dataList.add(MyItemData(14, false))
        dataList.add(MyItemData(15, false))

        recyclerViewAdapter = RecyclerViewAdapter(this, dataList)
        rv_item.adapter = recyclerViewAdapter
        rv_item.layoutManager = GridLayoutManager(this, 3)
    }
}
