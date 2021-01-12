package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import com.example.myapplication.databinding.ActivityThreadBinding

class ThreadActivity : AppCompatActivity() {
    private lateinit var binding: ActivityThreadBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_thread)
        binding = ActivityThreadBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val runnable: Runnable = object : Runnable {
            override fun run() {
                Log.d("thread-1", "Thread1 is made")
            }
        }

        binding.button.setOnClickListener {
            val thread: Thread = Thread(runnable)
            thread.start()
        }

        Thread(object : Runnable {
            override fun run() {
                Log.d("thread-2", "Thread2 is made")
            }
        }).start()

        Thread(Runnable {
            Thread.sleep(2000)
            Log.d("thread-3", "Thread3 is made")
            binding.button.setBackgroundColor(getColor(R.color.textview_color))
        }).start()

        // 메인쓰레드에서 작동하는 쓰레드드
       runOnUiThread {

        }

    }
}