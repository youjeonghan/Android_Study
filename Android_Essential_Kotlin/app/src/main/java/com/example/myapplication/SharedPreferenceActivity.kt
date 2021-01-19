package com.example.myapplication

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.myapplication.databinding.ActivitySharedPreferenceBinding

class SharedPreferenceActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySharedPreferenceBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySharedPreferenceBinding.inflate(layoutInflater)
        setContentView(binding.root)


        // Mode
        // - MODE_PRIVATE : 생성한 application에서만 사용 가능
        // - MODE_WORLD_READABLE : 다른 application에서 사용가능 -> 읽을 수만 있다
        // - MODE_WORLD_WRITEABLE : 다른 application에서 사용가능 -> 기록도 가능하다
        // - MODE_MULTI_PROCESS : 이미 호출되어 사용중인지 체크
        // - MODE_APPEND : 기존 preference에 신규로 추가


        // sp1 -> SHaredpreference
        //        (Key,Value) -> ("Hello", "안녕하세요")
        // sp2 -> SHaredpreference
        //        (Key,Value) -> ("Hello", "안녕하세요11")

        binding.saveBtn.setOnClickListener {
            // SharedPreference 에 저장하는 방법
            val sharedPreference = getSharedPreferences("sp1", MODE_PRIVATE)
            val editor = sharedPreference.edit()
            editor.putString("Hello", "안녕하세요")
            editor.putString("Goodbye", "안녕히가세요")
            editor.commit()
        }

        binding.deleteButton.setOnClickListener {
            val sharedPreference = getSharedPreferences("sp1", MODE_PRIVATE)
            val editor = sharedPreference.edit()
            editor.remove("Hello")      // 특정 키값만 지우기
            editor.commit()
        }

        binding.deleteAllButton.setOnClickListener {
            val sharedPreference = getSharedPreferences("sp1", MODE_PRIVATE)
            val editor = sharedPreference.edit()
            editor.clear()      // 전부 지우기
            editor.commit()
        }

        binding.loadButton.setOnClickListener {
            // SharedPreference 에서 값을 불러오는 방법
            val sharedPreference = getSharedPreferences("sp1", MODE_PRIVATE)
            val value1 = sharedPreference.getString("Hello", "데이터 없음")
            val value2 = sharedPreference.getString("Goodbye", "데이터 없음")
            Log.d("key-value", "Value1 : " + value1)
            Log.d("key-value", "Value2 : " + value2)
        }


    }
}