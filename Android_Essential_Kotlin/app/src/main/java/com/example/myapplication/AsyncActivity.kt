package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.databinding.ActivityAsyncBinding

class AsyncActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAsyncBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAsyncBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}

//class BackgroundAsyncTask(
//    val progressbar: ProgressBar,
//    val progressText: TextView
//) : AsyncTask<Int, Int, Int>() {
//    // parms -> doInBackground 에서 사용할 타입
//    // progress -> onProgressUpdate 에서 사용할 타입
//    // result -> onPostExcute 에서 사용할 타입
//}