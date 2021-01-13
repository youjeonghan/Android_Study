package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.databinding.ActivityPhoneBookDetailBinding

class PhoneBookDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPhoneBookDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPhoneBookDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getPersonInfoAndDraw()

        binding.back.setOnClickListener {
            onBackPressed()
        }
    }

    fun getPersonInfoAndDraw() {
        val name = intent.getStringExtra("name")
        val number = intent.getStringExtra("number")

        binding.personDetailName.setText(name)
        binding.personDetailNumber.setText(number)
    }
}