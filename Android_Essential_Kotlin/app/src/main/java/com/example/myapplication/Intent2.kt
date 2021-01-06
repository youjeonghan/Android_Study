package com.example.myapplication

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.myapplication.Kotlin.number
import com.example.myapplication.databinding.ActivityIntent2Binding

class Intent2 : AppCompatActivity() {

    private lateinit var binding: ActivityIntent2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intent2)

        binding = ActivityIntent2Binding.inflate(layoutInflater)
        setContentView(binding.root)



        binding.result.setOnClickListener {
            val number1 = intent.getIntExtra("number1", 0)
            val number2 = intent.getIntExtra("number2", 0)

            Log.d("number", "" + number1)
            Log.d("number", "" + number2)

            val result = number1 + number2

            val resultIntent = Intent()
            resultIntent.putExtra("result", result)
    
            // 응답 전송
            setResult(Activity.RESULT_OK, resultIntent)
            this.finish()    // -> Activity 종료

            // Stack의 개념
            // Intent2  -> 종료
            // Intent1          Intent1
        }
    }
}