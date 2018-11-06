package com.hyeran.android.a3rdseminar

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter

class MyFragmentStatePagerAdapter(fm : FragmentManager, val fragmentCont : Int) : FragmentStatePagerAdapter(fm) {
    override fun getItem(position: Int): Fragment? {
        when(position) {
            0 -> return MainFragment()
            1 -> return MapFragment()
            2 -> return MyPageFragment()
            else -> return null
        }
    }

    override fun getCount(): Int = fragmentCont
}