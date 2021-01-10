package com.example.myapplication

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import com.example.myapplication.databinding.ActivityOpenInternetBinding

class OpenInternet : AppCompatActivity() {
    private lateinit var binding: ActivityOpenInternetBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_open_internet)

        binding = ActivityOpenInternetBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.resultButton.setOnClickListener {
            var url = binding.inputInternetAddress.text.toString()
            Log.d("주소", url)
            // 암시적 인텐트
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)
        }

        binding.inputInternetAddress.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                Log.d("edit", "afterTextChanged " + s)
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                Log.d("edit", "onTextChanged " + s)
            }

            override fun afterTextChanged(s: Editable?) {
                Log.d("edit", "afterTextChanged " + s)
            }
        })

    }
}