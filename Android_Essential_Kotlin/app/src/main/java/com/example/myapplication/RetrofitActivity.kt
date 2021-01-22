package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.databinding.ActivityRetrofitBinding
import retrofit2.Retrofit

class RetrofitActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRetrofitBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRetrofitBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // http://mellowcode.org/json/students/
        // http://mellowcode.org/test/code/
        val retrofit = Retrofit.Builder()
            .baseUrl("http://mellowcode.org/")
            .build()

        val service = retrofit.create(RetrofitService::class.java)
    }
}