package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class NullSafety : AppCompatActivity() {

    lateinit var lateCar: Car

    class Car(val number:Int){
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_null_safety)

        val number: Int = 10
        val number1: Int? = null

        // !! -> 개발자가 null이 아님을 보장
        val number5:Int = number1!! + 10


        val number3 = number1?.plus(number)
        Log.d("number3", number3.toString())

        // 삼항연산자 -> 엘비스 연산자(?:) (null 이면 오른쪽에 있는게 들어감)
        // Null safety 를 위한 도구
        val number4 = number1 ?: 10
        Log.d("number4", number4.toString())

        lateCar = Car(10)
        Log.d("late_number", lateCar.number.toString())

    }

    fun plus(a: Int, b: Int?): Int {
        if (b != null) return a + b
        else return a
    }

    fun plus2(a: Int, b: Int?): Int? {
        return b?.plus(a)
    }


}