package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.databinding.ActivityIntent1Binding

class Intent1 : AppCompatActivity() {

    private lateinit var  binding: ActivityIntent1Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intent1)

        binding = ActivityIntent1Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.changeActivity.setOnClickListener {
            val intent = Intent(this@Intent1, Intent2::class.java)
            startActivity(intent)
        }

    }
}