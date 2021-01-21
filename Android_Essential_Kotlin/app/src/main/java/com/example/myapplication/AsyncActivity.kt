package com.example.myapplication


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ProgressBar
import android.widget.TextView
import com.example.myapplication.databinding.ActivityAsyncBinding

class AsyncActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAsyncBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAsyncBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}
