package com.example.myapplication

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import com.example.myapplication.databinding.ActivityOutStargramUploadBinding
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File

class OutStargramUploadActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOutStargramUploadBinding
    lateinit var filePath: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOutStargramUploadBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.viewPictures.setOnClickListener {
            getPicture()
        }

        binding.uploadPost.setOnClickListener {
            uploadPost()
        }


        binding.allList.setOnClickListener {
            startActivity(
                Intent(
                    this@OutStargramUploadActivity,
                    OutStargramPostListActivity::class.java
                )
            )
        }
        binding.userInfo.setOnClickListener {
            startActivity(
                Intent(
                    this@OutStargramUploadActivity,
                    OutstargramUserinfo::class.java
                )
            )
        }
        binding.upload.setOnClickListener {
            startActivity(
                Intent(
                    this@OutStargramUploadActivity,
                    OutStargramUploadActivity::class.java
                )
            )
        }
        binding.myList.setOnClickListener {
            startActivity(
                Intent(
                    this@OutStargramUploadActivity,
                    OutStargramMyPostListActivity::class.java
                )
            )
        }
    }

    fun getPicture() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        intent.setType("image/*")
        startActivityForResult(intent, 1000)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1000) {
            val uri: Uri = data!!.data!!        // 이위치는 실제 파일위치와 다름 (상대주소 같은것)
            Log.d("pathh1", "" + uri)
            filePath = getImageFilepath(uri)
            Log.d("result", "" + filePath)
        }
    }

    // 경로를 찾는 함수
    fun getImageFilepath(contentUri: Uri): String {
        var columnIndex = 0
        val projection = arrayOf(
            MediaStore.Images.Media.DATA
        )      // 걸래내기위한 틀
        val cursor = contentResolver.query(contentUri, projection, null, null, null)
//        cursor!!.moveToNext()
        if (cursor!!.moveToFirst()) {
            columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
        }
        return cursor.getString(columnIndex)
    }

    fun uploadPost() {
        Log.d("uploadPost", "play")
        val file = File(filePath)
        Log.d("file", "" + file)
        val fileRequestBody = RequestBody.create(MediaType.parse("image/*"), file)
        val part = MultipartBody.Part.createFormData("image", file.name, fileRequestBody)
        val content = RequestBody.create(MediaType.parse("text/plain"), getContent())

        (application as MasterApplication).service.uploadPost(
            part, content
        ).enqueue(object : Callback<Post> {
            override fun onResponse(call: Call<Post>, response: Response<Post>) {
                if (response.isSuccessful) {
                    finish()
                    startActivity(
                        Intent(
                            this@OutStargramUploadActivity,
                            OutStargramPostListActivity::class.java
                        )
                    )
                }
            }

            override fun onFailure(call: Call<Post>, t: Throwable) {
                Log.d("fail", "fail")
            }
        })
    }

    fun getContent(): String {
        return binding.contentInput.text.toString()
    }

}