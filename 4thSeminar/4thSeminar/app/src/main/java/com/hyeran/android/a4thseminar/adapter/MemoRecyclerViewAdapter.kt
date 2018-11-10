package com.hyeran.android.a4thseminar.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import com.hyeran.android.a4thseminar.data.MemoData
import com.hyeran.android.a4thseminar.R

class MemoRecyclerViewAdapter(val ctx : Context, val dataList : ArrayList<MemoData>) : RecyclerView.Adapter<MemoRecyclerViewAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view : View = LayoutInflater.from(ctx).inflate(R.layout.rv_item, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.title.text = dataList[position].title
        holder.content.text = dataList[position].content

        holder.whole_box.setOnClickListener {

            // 너무 빨리 누르면 터지므로 예외 처리
            try {
                dataList.removeAt(position)
                notifyItemRemoved(position)
                notifyItemRangeChanged(position, dataList.size)
            } catch (e : IndexOutOfBoundsException) {
                Log.e("Index error", e.toString())
            }
        }
    }


    inner class Holder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val title : TextView = itemView.findViewById(R.id.tv_rv_item_memo_title) as TextView
        val content : TextView = itemView.findViewById(R.id.tv_rv_item_memo_content) as TextView
        val whole_box : RelativeLayout = itemView.findViewById(R.id.btn_rv_item_memo_whole_box) as RelativeLayout
    }
}