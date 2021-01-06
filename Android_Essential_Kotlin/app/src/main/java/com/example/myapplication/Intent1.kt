package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.databinding.ActivityIntent1Binding

class Intent1 : AppCompatActivity() {

    private lateinit var binding: ActivityIntent1Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intent1)

        binding = ActivityIntent1Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.changeActivity.setOnClickListener {
            val intent = Intent(this@Intent1, Intent2::class.java)

//            // Key, Value 방식 -> Key와 value를 쌍으로 만들어 저장한다.
//            intent.putExtra("number1", 1)
//            intent.putExtra("number2", 2)
//            startActivity(intent)

            val intent2 = Intent(this@Intent1, Intent2::class.java)
            // Apply -> 사용하면 유지보수 및 실수가 준다
            intent2.apply {
                this.putExtra("nummber1", 1)
                this.putExtra("nummber2", 2)
            }
            startActivity(intent2)
        }

    }
}