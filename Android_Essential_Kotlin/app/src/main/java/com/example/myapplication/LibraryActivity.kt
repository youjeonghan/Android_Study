package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.myapplication.databinding.ActivityLibraryBinding

class LibraryActivity : AppCompatActivity() {
    private lateinit var binding : ActivityLibraryBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = ActivityLibraryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Glide.with(this)
            .load("https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FC2N5z%2FbtqE1vHnxAB%2FvdQl62PFivrFoDWy2jWjp1%2Fimg.png")
            .centerCrop()
            .into(binding.glide)

    }
}