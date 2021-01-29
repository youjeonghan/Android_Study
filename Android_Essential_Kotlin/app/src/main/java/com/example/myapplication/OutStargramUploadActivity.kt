package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.databinding.ActivityOutStargramUploadBinding

class OutStargramUploadActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOutStargramUploadBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOutStargramUploadBinding.inflate(layoutInflater)
        setContentView(binding.root)

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
}