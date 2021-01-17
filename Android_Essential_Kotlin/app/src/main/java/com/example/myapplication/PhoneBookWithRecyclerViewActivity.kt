package com.example.myapplication

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ActivityPhoneBookWithRecyclerViewBinding
import com.example.myapplication.databinding.PhonebookItemBinding
import java.security.AccessController.getContext

class PhoneBookWithRecyclerViewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPhoneBookWithRecyclerViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPhoneBookWithRecyclerViewBinding.inflate(layoutInflater)
        setContentView(binding.root)



        with(binding.phonebookRecyclerView) {
            this.adapter = PhoneBookRecyclerAdapter(
                itemList = createFakePhoneBook(),
                activity = this@PhoneBookWithRecyclerViewActivity
            )
            this.layoutManager =
                LinearLayoutManager(this@PhoneBookWithRecyclerViewActivity)
        }


    }

    fun createFakePhoneBook(
        fakeNumber: Int = 30,
        phoneBook: PhoneBook1 = PhoneBook1()
    ): PhoneBook1 {
        for (i in 0 until fakeNumber) {
            phoneBook.addPerson(
                Person(name = "" + i + "번째 사람", number = "" + i + "번째 사람의 전화 번호")
            )
        }
        return phoneBook
    }
}

class PhoneBookRecyclerAdapter(
    val itemList: PhoneBook1,
    val activity: Activity
//    val inflater: LayoutInflater
) : RecyclerView.Adapter<PhoneBookRecyclerAdapter.ViewHolder>() {

    inner class ViewHolder(itemView_binding: PhonebookItemBinding) :
        RecyclerView.ViewHolder(itemView_binding.root) {
        val personName: TextView


        init {
            personName = itemView_binding.personName
            addSetOnClickListener(
                itemList,
                itemView_binding.root,
                activity
            )
//            itemView_binding.root.setOnClickListener {
//                val position: Int = adapterPosition
//                val name = itemList.personList[position].name
//                Log.d("name", name)
//            }
        }

        // 리스너 장착 함수
        fun addSetOnClickListener(itemList: PhoneBook1, view: View, activity: Activity) {
            Log.d("리스너 장착함수", " 리스너 장착함수")
            view.setOnClickListener {
                val intent = Intent(activity, PhoneBookDetailActivity::class.java)
                intent.putExtra("name", itemList.personList[adapterPosition].name)
                intent.putExtra("number", itemList.personList[adapterPosition].number)
                // 해당 라인에서는 startActivity() 실행 안됨 (AppCompatActivity()를 상속 받지 않기 때문 / AppCompatActivity()가 가지고있는 기능임)
                // class에서 반은 인자인 activity 안에 기능이 상속 받아져있다
                activity.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.phonebook_item, parent, false)

        return ViewHolder(PhonebookItemBinding.bind(view))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.personName.setText(itemList.personList.get(position).name)
    }

    override fun getItemCount(): Int {
        return itemList.personList.size
    }
}


class PhoneBook1() {
    // 전화 번호부
    val personList = ArrayList<Person>()

    fun addPerson(person: Person) {
        personList.add(person)
    }
}