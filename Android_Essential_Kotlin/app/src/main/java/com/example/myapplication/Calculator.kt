package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.databinding.ActivityCalculatorBinding

class Calculator : AppCompatActivity() {

    var sum_cal = 0
    var result_cal = 0

    // 뷰바인딩 (1),(2),(3) 참조
    // 액티비티에서 사용할 레이아웃의 뷰 바인딩 클래스 (1)
    private lateinit var binding: ActivityCalculatorBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculator)

        // 뷰 바인딩 클래스의 인스턴스를 생성합니다. (2)
        binding = ActivityCalculatorBinding.inflate(layoutInflater)

        // 생성된 뷰를 액티비티에 표시합니다. (3)
        setContentView(binding.root)

        // 초기화
        binding.CA.setOnClickListener {
            result_cal = 0
            sum_cal = 0
            binding.result.setText("0")
        }

        // 덧셈
        binding.plus.setOnClickListener {
            result_cal += sum_cal
            sum_cal = 0
            binding.result.setText(result_cal.toString())
        }

        // 0~9 키패드
        binding.zero.setOnClickListener {
            sum_cal = (sum_cal * 10)
            binding.result.setText(sum_cal.toString())
        }
        binding.one.setOnClickListener {
            sum_cal = (sum_cal * 10) + 1
            binding.result.setText(sum_cal.toString())
        }
        binding.two.setOnClickListener {
            sum_cal = (sum_cal * 10) + 2
            binding.result.setText(sum_cal.toString())
        }
        binding.three.setOnClickListener {
            sum_cal = (sum_cal * 10) + 3
            binding.result.setText(sum_cal.toString())
        }
        binding.four.setOnClickListener {
            sum_cal = (sum_cal * 10) + 4
            binding.result.setText(sum_cal.toString())
        }
        binding.five.setOnClickListener {
            sum_cal = (sum_cal * 10) + 5
            binding.result.setText(sum_cal.toString())
        }
        binding.six.setOnClickListener {
            sum_cal = (sum_cal * 10) + 6
            binding.result.setText(sum_cal.toString())
        }
        binding.seven.setOnClickListener {
            sum_cal = (sum_cal * 10) + 7
            binding.result.setText(sum_cal.toString())
        }
        binding.eight.setOnClickListener {
            sum_cal = (sum_cal * 10) + 8
            binding.result.setText(sum_cal.toString())
        }
        binding.nine.setOnClickListener {
            sum_cal = (sum_cal * 10) + 9
            binding.result.setText(sum_cal.toString())
        }
    }
}