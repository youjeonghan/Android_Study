package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.MediaController
import com.example.myapplication.databinding.ActivityMytubeDetailBinding

class MytubeDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMytubeDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMytubeDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val url = intent.getStringExtra("video_url")
        Log.d("test", "" + url)
        val mediaController = MediaController(this@MytubeDetailActivity)
        binding.videoView.setVideoPath(url)
        binding.videoView.requestFocus()
        binding.videoView.start()
        mediaController.show()

        // Exoplayer (영상을 전문적으로 다룰려면)
        // drm - digital right management   (아무나 다운못받게 하는것)
    }
}