package com.hyeran.android.a3rdseminar_hw

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import org.jetbrains.anko.toast

class RecyclerViewAdapter(val ctx : Context, val dataList : ArrayList<MyItemData>) : RecyclerView.Adapter<RecyclerViewAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view : View = LayoutInflater.from(ctx).inflate(R.layout.rv_item, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int = dataList.size

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.counter.text = dataList[position].counter.toString()
        holder.itemView.setOnClickListener {
            ctx.toast(holder.counter.text)
            var isLike = dataList[position].isLike
            if(isLike) {
                dataList[position].isLike = false
                holder.like.visibility = View.INVISIBLE
            } else {
                dataList[position].isLike = true
                holder.like.visibility = View.VISIBLE
            }
        }
    }

    inner class Holder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val like : ImageView = itemView.findViewById(R.id.iv_like_item) as ImageView
        val counter : TextView = itemView.findViewById(R.id.tv_counter_item) as TextView
    }
}