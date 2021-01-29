package com.example.myapplication

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import com.example.myapplication.databinding.ActivityOutStargramUploadBinding

class OutStargramUploadActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOutStargramUploadBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOutStargramUploadBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.viewPictures.setOnClickListener {
            getPicture()
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
            val a = getImageFilepath(uri)
            Log.d("pathh2", "" + a)
        }
    }

    // 경로를 찾는 함수
    fun getImageFilepath(contentUri: Uri): String {
        var columnIndex = 0
        val projection = arrayOf(MediaStore.Images.Media._ID)      // 걸래내기위한 틀
        val cursor = contentResolver.query(contentUri, projection, null, null, null)
        if (cursor!!.moveToFirst()) {
            columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media._ID)
//            var imageId = cursor.getLong(columnIndex)
//            val uriImage = Uri.withAppendedPath(contentUri, "" + imageId)
//            Log.d("getImageFilepath1", "" + uriImage)
        }
        Log.d("getImageFilepath2", "" + columnIndex)
        return cursor.getString(columnIndex)
    }
}