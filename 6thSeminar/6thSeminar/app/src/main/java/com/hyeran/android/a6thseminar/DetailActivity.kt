package com.hyeran.android.a6thseminar

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.hyeran.android.a6thseminar.data.BoardData
import com.hyeran.android.a6thseminar.db.SharedPreferencesController
import com.hyeran.android.a6thseminar.delete.DeleteBoardResponse
import com.hyeran.android.a6thseminar.get.BoardDetailData
import com.hyeran.android.a6thseminar.get.GetBoardDetailResponse
import com.hyeran.android.a6thseminar.network.ApplicationController
import com.hyeran.android.a6thseminar.network.NetworkService
import kotlinx.android.synthetic.main.activity_detail.*
import org.jetbrains.anko.ctx
import org.jetbrains.anko.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailActivity : AppCompatActivity() {

    val networkService: NetworkService by lazy {
        ApplicationController.instance.networkService
    }

    var contentIdx = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        getBoardDetailResponse()

        btn_delete_detail.setOnClickListener {
            val token = SharedPreferencesController.getAuthorization(this)

            val deleteBulletinResponse = networkService.deleteBoardResponse("application/json", token, contentIdx)
            deleteBulletinResponse.enqueue(object: Callback<DeleteBoardResponse> {
                override fun onFailure(call: Call<DeleteBoardResponse>?, t: Throwable?) {
                    Log.e("<게시판-게시글 삭제> 통신 Fail", t.toString())
                }

                override fun onResponse(call: Call<DeleteBoardResponse>?, response: Response<DeleteBoardResponse>) {
                    if (response.isSuccessful) {
                        ctx.toast(response.body()!!.message)
                        finish()
                    } else {
                        Log.e("<게시판-게시글 삭제> 응답 Fail: ", response.code().toString())
                        Log.e("<게시판-게시글 삭제> 응답 Fail: ", response.errorBody().toString())
                    }
                }

            })
        }
    }

    private fun getBoardDetailResponse() {
        val token = SharedPreferencesController.getAuthorization(this)
        val contentIdx = intent.getIntExtra("contentIdx", 0)
        val getBoardDetailResponse = networkService.getBoardDetailResponse("application/json", token, contentIdx)
        getBoardDetailResponse.enqueue(object : Callback<GetBoardDetailResponse> {
            override fun onFailure(call: Call<GetBoardDetailResponse>?, t: Throwable?) {
                Log.e("<게시판-게시글 상세> fail", t.toString())
            }

            override fun onResponse(call: Call<GetBoardDetailResponse>?, response: Response<GetBoardDetailResponse>) {
                if (response.isSuccessful){
                    toast(response.body()!!.message)
                    val temp : BoardDetailData = response.body()!!.data!!
                    tv_title_detail.text = temp.b_title
                    tv_content_detail.text = temp.b_contents
                    this@DetailActivity.contentIdx = temp.b_id

                    val requestOptions = RequestOptions()
            //        requestOptions.placeholder(R.drawable.기본적으로 띄울 이미지)
            //        requestOptions.error(R.drawable.에러시 띄울 이미지)
            //        requestOptions.override(150) - 사진 크기를 줄일 수 있다.
                    Glide.with(this@DetailActivity)
                            .setDefaultRequestOptions(requestOptions)
                            .load(temp.b_photo)
                            .thumbnail(0.5f)
                            .into(iv_photo_detail)
                } else {
                    Log.e("<게시판-게시글 상세> 응답 fail: ", response.code().toString())
                    Log.e("<게시판-게시글 상세> 응답 fail: ", response.errorBody().toString())
                }
            }
        })
    }
}
