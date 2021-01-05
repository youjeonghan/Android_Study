package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import com.example.myapplication.databinding.ActivityListenerBinding

class Linstener : AppCompatActivity() {

    var number = 10

    // 2번 방법 대신 뷰바인딩을 사용하는게 정석이라고함 (1),(2),(3) 참조
    // 액티비티에서 사용할 레이아웃의 뷰 바인딩 클래스 (1)
    private lateinit var binding: ActivityListenerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listener)

        // 뷰 바인딩 클래스의 인스턴스를 생성합니다. (2)
        binding = ActivityListenerBinding.inflate(layoutInflater)

        // 생성된 뷰를 액티비티에 표시합니다. (3)
        setContentView(binding.root)

        // 뷰를 activity로 가져오는 방법
        // 1 -> 직접 찾아서 가져온다
//        val textView: TextView = findViewById(R.id.hello)

        // 2 -> xml을 import해서 가져온다  4.1버전부터 사라진 기능 추가해서 사용이 가능하긴하다
//        hello

        // 익명함수
        // 1 -> 람다 방식
        binding.hello.setOnClickListener {
            Log.d("click", "Click!!")
        }

        // 2 -> 익명 함수 방식 (특별히 이름이 없음ㅎㅎ)
        binding.hello.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                Log.d("click", "Click!!")
            }
        })

        // 3 -> 이름이 필요한 경우 (이름:click / 다른이름이여도 상관없음)
        val click = object : View.OnClickListener {
            override fun onClick(v: View?) {
                Log.d("click", "Click!!")
                binding.hello.setText("안녕하세요")
                binding.image.setImageResource(R.drawable.people)
                number += 10
                Log.d("number", number.toString())
            }
        }

        binding.hello.setOnClickListener(click)


        // 뷰를 조작 하는 함수들
        // 1 -> setText
        // 2 -> setImage
        // 3 ->


    }

}