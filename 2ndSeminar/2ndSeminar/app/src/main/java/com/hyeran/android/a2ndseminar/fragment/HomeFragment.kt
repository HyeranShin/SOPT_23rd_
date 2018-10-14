package com.hyeran.android.a2ndseminar.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hyeran.android.a2ndseminar.R

class HomeFragment : Fragment() {

    // 싱글톤
    // 자바로치면 static
    companion object {
        var mInstance : HomeFragment? = null

        @Synchronized
        fun getInstance() : HomeFragment {
            if (mInstance == null) {
                mInstance = HomeFragment()
            }
            return mInstance!!
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view : View = inflater.inflate(R.layout.fragment_home, container, false)

        return view
    }
}