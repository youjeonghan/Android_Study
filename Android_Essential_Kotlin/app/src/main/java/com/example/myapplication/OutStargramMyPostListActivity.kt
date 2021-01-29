package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.databinding.ActivityOutStargramMyPostListBinding

class OutStargramMyPostListActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOutStargramMyPostListBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOutStargramMyPostListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.allList.setOnClickListener {
            startActivity(
                Intent(
                    this@OutStargramMyPostListActivity,
                    OutStargramPostListActivity::class.java
                )
            )
        }
        binding.userInfo.setOnClickListener {
            startActivity(
                Intent(
                    this@OutStargramMyPostListActivity,
                    OutstargramUserinfo::class.java
                )
            )
        }
        binding.upload.setOnClickListener {
            startActivity(
                Intent(
                    this@OutStargramMyPostListActivity,
                    OutStargramUploadActivity::class.java
                )
            )
        }
        binding.myList.setOnClickListener {
            startActivity(
                Intent(
                    this@OutStargramMyPostListActivity,
                    OutStargramMyPostListActivity::class.java
                )
            )
        }
    }
}