package com.hyeran.android.a2ndseminar

import android.app.Activity
import android.app.FragmentTransaction
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import com.hyeran.android.a2ndseminar.fragment.HomeFragment
import com.hyeran.android.a2ndseminar.fragment.UserFragment
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivityForResult
import org.jetbrains.anko.toast

class MainActivity : AppCompatActivity() {

//    // 인스턴스 변수
//    var data1 : String? = null
//    var data2 : String? = null
//    var data3 : Int = 0
//
//    val REQUEST_CODE_USER_ACTIVITY = 1004
//
//    var backPressedTime: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        addFragment(HomeFragment())

        btn_main_act_home_frag.setOnClickListener {
            replaceFragment(HomeFragment())
        }
        btn_main_act_user_frag.setOnClickListener {
            replaceFragment(UserFragment())
        }

//        btn_main_act_goto_sub.setOnClickListener {
//
//            // 버튼을 눌렀을 때 수행할 로직 작성
////            startActivityForResult<UserActivity>(REQUEST_CODE_USER_ACTIVITY, "data1" to "안녕하세요!")
//
//            val intent : Intent = Intent(this,  UserActivity::class.java)
//            intent.putExtra("data1", "안녕하세요!")
//            startActivityForResult(intent, REQUEST_CODE_USER_ACTIVITY)
//        }
//
//        //------------------------------------------------------------------------------------------
//
//        data1 = intent.getStringExtra("data1")
//
////        val data2 = intent.getStringExtra("data2")
//        // null 이면 블록 안의 코드가 수행되지 않음
//        intent.getStringExtra("data2")?.let {
//            data2 = it
//        }
//
//        data3 = intent.getIntExtra("data3", 0)

    }

    private fun addFragment(fragment : Fragment) {
        // transaction이 수행되는 동안 다른 작업과 꼬이는 것을 방지
        val transaction : android.support.v4.app.FragmentTransaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.fl_main_act_fragment, fragment)
        transaction.commit()
    }

    private fun replaceFragment(fragment: Fragment) {
        val transaction : android.support.v4.app.FragmentTransaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fl_main_act_fragment, fragment)
        transaction.commit()
    }

//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//
//        if (requestCode == REQUEST_CODE_USER_ACTIVITY) {
//            if (resultCode == 100) {
//                toast("결과 코드 100!")
//            } else if (resultCode == 200) {
//                toast("결과 코드 200!")
//            } else if (resultCode == RESULT_OK) {
//                toast("결과 코드 RESULT_OK!")
//            }
//        }
//    }
//
//    override fun onBackPressed() {
//        var temp: Long = System.currentTimeMillis()
//        var intervalTime: Long = temp - backPressedTime
//        if (intervalTime in 0..2000) {
//            super.onBackPressed()
//        } else {
//            backPressedTime = temp
//            toast("버튼을 한번 더 누르면 종료됩니다.")
//        }
//    }
}
