package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import android.widget.Toast
import com.example.myapplication.databinding.ActivityListViewBinding
import com.example.myapplication.databinding.ItemViewBinding

class ListViewActivity : AppCompatActivity() {
    private lateinit var binding: ActivityListViewBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 아이템 리스트 준비
        val carList = ArrayList<CarForList>()
        for (i in 0 until 10) {
            carList.add(CarForList("" + i + "번째 자동차", "" + i + "순위 엔진"))
        }

        val adapter = ListViewAdapter(carList, LayoutInflater.from(this@ListViewActivity))
        binding.listView.adapter = adapter
        binding.listView.setOnItemClickListener { parent, view, position, id ->
            val carName = (adapter.getItem(position) as CarForList).name
            val carEngine = (adapter.getItem(position) as CarForList).engine

            Toast.makeText(this@ListViewActivity, carName + " " + carEngine, Toast.LENGTH_SHORT).show()

        }

    }
}

//// 뷰바인딩 버전
//class ListViewAdapter(val carForList: ArrayList<CarForList>, val layoutInflater: LayoutInflater) :
//    BaseAdapter() {
//    override fun getCount(): Int {
//        // 그리고자 하는 아이템 리스트의 전체 갯수
//        return carForList.size
//    }
//
//    override fun getItem(position: Int): Any {
//        // 그리고자 하는 아이템 리스트의 하나(해당 포지션의 하나)
//        return carForList[position]
//    }
//
//    override fun getItemId(position: Int): Long {
//        // 해당 포지션에 위치해 있는 아이템뷰의 아이디 설정
//        return position.toLong()
//    }
//
//    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
//        val view = ItemViewBinding.inflate(layoutInflater)
//        val carNameTextview = view.carName
//        val carEngineTextview = view.carEngine
//
//        carNameTextview.setText(carForList[position].name)
//        carEngineTextview.setText(carForList[position].engine)
//        return view.root
//    }
//}

// findViewById 버전
// class ListViewAdapter(val carForList: ArrayList<CarForList>, val layoutInflater: LayoutInflater) : BaseAdapter() {
//    override fun getCount(): Int {
//        // 그리고자 하는 아이템 리스트의 전체 갯수
//        return carForList.size
//    }
//
//    override fun getItem(position: Int): Any {
//        // 그리고자 하는 아이템 리스트의 하나(해당 포지션의 하나)
//        return carForList[position]
//    }
//
//    override fun getItemId(position: Int): Long {
//        // 해당 포지션에 위치해 있는 아이템뷰의 아이디 설정
//        return position.toLong()
//    }
//
//    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
//        val view = layoutInflater.inflate(R.layout.item_view, null)
//        val carNameTextview = view.findViewById<TextView>(R.id.car_name)
//        val carEngineTextview = view.findViewById<TextView>(R.id.car_engine)
//
//        carNameTextview.setText(carForList[position].name)
//        carEngineTextview.setText(carForList[position].engine)
//        return view
//    }
//}

// ViewHolder 사용 버전
 class ListViewAdapter(val carForList: ArrayList<CarForList>, val layoutInflater: LayoutInflater) : BaseAdapter() {
    override fun getCount(): Int {
        // 그리고자 하는 아이템 리스트의 전체 갯수
        return carForList.size
    }

    override fun getItem(position: Int): Any {
        // 그리고자 하는 아이템 리스트의 하나(해당 포지션의 하나)
        return carForList[position]
    }

    override fun getItemId(position: Int): Long {
        // 해당 포지션에 위치해 있는 아이템뷰의 아이디 설정
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        val view = layoutInflater.inflate(R.layout.item_view, null)
        val carNameTextview = view.findViewById<TextView>(R.id.car_name)
        val carEngineTextview = view.findViewById<TextView>(R.id.car_engine)

        carNameTextview.setText(carForList[position].name)
        carEngineTextview.setText(carForList[position].engine)
        return view
    }
}