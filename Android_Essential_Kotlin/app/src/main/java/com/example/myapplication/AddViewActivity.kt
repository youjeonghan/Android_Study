package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.TextView
import com.example.myapplication.databinding.ActivityAddViewBinding
import com.example.myapplication.databinding.ItemViewBinding

class AddViewActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddViewBinding
    private lateinit var binding2: ItemViewBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddViewBinding.inflate(layoutInflater)
        binding2 = ItemViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 아이템 리스트 준비
        val carList = ArrayList<CarForList>()
        for (i in 0 until 10) {
            carList.add(CarForList("" + i + "번째 자동차", "" + i + "순위 엔진"))
        }

        val container = binding.addviewContainer
        val inflater = LayoutInflater.from(this)
        for (i in 0 until carList.size) {
            val itemView = inflater.inflate(R.layout.item_view, null)
            val carNameView = itemView.findViewById<TextView>(R.id.car_name)
            val carEngineView = itemView.findViewById<TextView>(R.id.car_engine)

            carNameView.setText(carList.get(i).name)
            carEngineView.setText(carList.get(i).engine)
            Log.d("cnt", i.toString())
            container.addView(itemView)
        }
    }
}

class CarForList(val name: String, val engine: String) {

}