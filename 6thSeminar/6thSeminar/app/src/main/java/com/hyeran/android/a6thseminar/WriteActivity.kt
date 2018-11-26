package com.hyeran.android.a6thseminar

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.bumptech.glide.Glide
import com.hyeran.android.a6thseminar.db.SharedPreferencesController
import com.hyeran.android.a6thseminar.network.ApplicationController
import com.hyeran.android.a6thseminar.network.NetworkService
import com.hyeran.android.a6thseminar.post.PostWriteBoardResponse
import kotlinx.android.synthetic.main.activity_write.*
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import org.jetbrains.anko.toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.InputStream

class WriteActivity : AppCompatActivity() {
    val REQUEST_CODE_SELECT_IMAGE: Int = 1004
    private var mImage: MultipartBody.Part? = null

    val networkService: NetworkService by lazy {
        ApplicationController.instance.networkService
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_write)
        setOnBtnClickListener()
    }

    private fun setOnBtnClickListener() {
        btn_write_act_show_album.setOnClickListener {
            // 앨범 여는 로직
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = android.provider.MediaStore.Images.Media.CONTENT_TYPE
            intent.data = android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            startActivityForResult(intent, REQUEST_CODE_SELECT_IMAGE)
        }
        btn_write_act_complete.setOnClickListener {
            getWriteBoardResponse()
        }
    }

    /*
    앨범에서 사진을 선택했을때 실행되는 메소드

    앨범에서 사진을 선택했을 때, onActivityResult로 사진을 받아오고,
    서버로 전송할 수 있는 image file로 변환하는 과정
    + Glide를 통해 ImageView에 사진 띄우는 로직
    mImage라는 인스턴스 변수에 서버로 전송할 수 있도록 이미지 파일 가공하여 넣음
     */
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {   // data에 사진의 URI(경로)가 들어있다.
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE_SELECT_IMAGE) {
            if (resultCode == Activity.RESULT_OK) { // 이 두 부분은 형식적
                data?.let {
                    var selectedPictureUri = it.data
                    val options = BitmapFactory.Options()
                    val inputStream: InputStream = contentResolver.openInputStream(selectedPictureUri)
                    val bitmap = BitmapFactory.decodeStream(inputStream, null, options)
                    val byteArrayOutputStream = ByteArrayOutputStream()
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 20, byteArrayOutputStream)
                    val photoBody = RequestBody.create(MediaType.parse("image/jpg"), byteArrayOutputStream.toByteArray())
                    //첫번째 매개변수 String을 꼭! 꼭! 서버 API에 명시된 이름으로 넣어주세요!!!
                    mImage = MultipartBody.Part.createFormData("photo", File(selectedPictureUri.toString()).name, photoBody)
                    //Glide을 사진 URI를 ImageView에 넣은 방식. 외부 URI가 아니라 굳이 Glide을 안써도 되지만 ㅎㅎ!
                    Glide.with(this@WriteActivity).load(selectedPictureUri).thumbnail(0.1f).into(iv_write_act_choice_image)
                }
            }
        }
    }

    // 데이터들을 RequestBody 객체로 만들어서 서버로 요청을 보내는 코드
    private fun getWriteBoardResponse() {
        val input_title = et_write_act_title.text.toString()
        val input_contents = et_write_act_content.text.toString()
        if (input_title.isNotEmpty() && input_contents.isNotEmpty()) {
            //Multipart 형식은 String을 RequestBody 타입으로 바꿔줘야 합니다!
            val token = SharedPreferencesController.getAuthorization(this)
            var title = RequestBody.create(MediaType.parse("text/plain"), input_title)
            var contents = RequestBody.create(MediaType.parse("text/plain"), input_contents)

            // 본격적인 통신
            val postWriteBoardResponse = networkService.postWriteBoardResponse(token, title, contents, mImage)

            postWriteBoardResponse.enqueue(object : Callback<PostWriteBoardResponse> {
                override fun onFailure(call: Call<PostWriteBoardResponse>, t: Throwable) {
                    Log.e("<게시판-게시글 작성> 통신 fail", t.toString())
                }

                override fun onResponse(call: Call<PostWriteBoardResponse>, response: Response<PostWriteBoardResponse>) {
                    if (response.isSuccessful) {
                        toast(response.body()!!.message)
                        finish()
                    } else {
                        Log.e("<게시판-게시글 작성> 응답 fail: ", response.code().toString())
                        Log.e("<게시판-게시글 작성> 응답 fail: ", response.errorBody().toString())
                    }
                }
            })
        }
    }
}