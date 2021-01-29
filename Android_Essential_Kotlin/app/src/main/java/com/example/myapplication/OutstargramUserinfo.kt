package com.example.myapplication

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.myapplication.databinding.ActivityOutstargramUserinfoBinding

class OutstargramUserinfo : AppCompatActivity() {
    private lateinit var binding: ActivityOutstargramUserinfoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOutstargramUserinfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.allList.setOnClickListener {
            startActivity(
                Intent(
                    this@OutstargramUserinfo,
                    OutStargramPostListActivity::class.java
                )
            )
        }
        binding.userInfo.setOnClickListener {
            startActivity(
                Intent(
                    this@OutstargramUserinfo,
                    OutstargramUserinfo::class.java
                )
            )
        }
        binding.upload.setOnClickListener {
            startActivity(
                Intent(
                    this@OutstargramUserinfo,
                    OutStargramUploadActivity::class.java
                )
            )
        }
        binding.myList.setOnClickListener {
            startActivity(
                Intent(
                    this@OutstargramUserinfo,
                    OutStargramMyPostListActivity::class.java
                )
            )
        }

        binding.logout.setOnClickListener {
            Log.d("로그아웃", "로그아웃")
            val sp = getSharedPreferences("login_sp", Context.MODE_PRIVATE)
            val editor = sp.edit()
            editor.putString("login_sp", "null")
            editor.commit()
            (application as MasterApplication).createRetrofit()
            finish()
            startActivity(
                Intent(this@OutstargramUserinfo, LoginActivity::class.java)
            )

        }
    }
}