package com.example.myapplication

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.myapplication.databinding.FragementOneBinding

class Fragment2 : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //        return inflater.inflate(R.layout.fragement_one, container, false)

        // 프라그먼트가 인터페이스를 처음으로 그릴 때 호출된다.
        // inflater -> 뷰를 그려주는 역할
        // container -> 부모뷰
        // Fragment에서 view 바인딩 사용법 (2)
//        _binding = FragementOneBinding.inflate(inflater, container, false)
//        return binding.root

        return inflater.inflate(R.layout.fragement_one, container, false)
    }


}