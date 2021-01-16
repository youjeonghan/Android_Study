package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ActivityRecyclerViewBinding

class RecyclerViewActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRecyclerViewBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecyclerViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // 아이템 리스트 준비
        val carList = ArrayList<CarForList>()
        for (i in 0 until 30) {
            carList.add(CarForList("" + i + "번째 자동차", "" + i + "순위 엔진"))
        }

        val adapter = RecyclerViewAdapter(carList, LayoutInflater.from(this@RecyclerViewActivity))
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this@RecyclerViewActivity)
//        binding.recyclerView.layoutManager = GridLayoutManager(this@RecyclerViewActivity, 2)
    }
}

class RecyclerViewAdapter(
    val itemList: ArrayList<CarForList>,
    val inflater: LayoutInflater
) : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val carName: TextView
        val carEngine: TextView

        init {
            carName = itemView.findViewById(R.id.car_name)
            carEngine = itemView.findViewById(R.id.car_engine)
            itemView.setOnClickListener {
                val position:Int = adapterPosition
                val engineName = itemList.get(position).engine
                Log.d("engine", engineName)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = inflater.inflate(R.layout.item_view, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.carName.setText(itemList.get(position).name)
        holder.carEngine.setText(itemList.get(position).engine)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }
}
// RecyclerView의 작동 과정
// onCreateViewHolder 함수에서 activity의 리사이클뷰에 들어갈 공간을 만든다.
// -> 이렇게 만들어진 공간을 ViewHolder에서 받는다 (해당 코드는 뷰를 받지만 뷰바인딩, 데이터 바인딩을 받아서 작동하게 만들 수도 있다.)
// -> ViewHolder에서는 이 공간에 들어갈 sub_item(.xml)의 정보를 불러와서 저장한다(골격을 잡는다)
// -> onBindViewHolder 함수에서 이 공간에 데이터를 담는다