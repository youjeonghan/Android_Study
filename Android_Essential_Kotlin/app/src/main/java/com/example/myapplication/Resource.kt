package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.myapplication.databinding.ActivityResourceBinding

class Resource : AppCompatActivity() {

    private lateinit var binding: ActivityResourceBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_resource)

        binding = ActivityResourceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 1
        val ment = resources.getString(R.string.hello)
        Log.d("ment", "ment: " + ment)

        // 2
        val ment2 = getString(R.string.hello)
        Log.d("ment", "ment: " + ment2)


        val color = getColor(R.color.textview_color)
        Log.d("ment", "color: " + color)

        binding.button.setBackgroundColor(getColor(R.color.black))

    }
}