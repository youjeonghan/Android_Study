package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ActivityPhoneBookWithRecyclerViewBinding
import com.example.myapplication.databinding.PhonebookItemBinding

class PhoneBookWithRecyclerViewActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPhoneBookWithRecyclerViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPhoneBookWithRecyclerViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val phone_book1 = createFakePhoneBook()

        val adapter = PhoneBookRecyclerAdapter(
            phone_book1,
            LayoutInflater.from(this@PhoneBookWithRecyclerViewActivity)
        )
        binding.phonebookRecyclerView.adapter = adapter
        binding.phonebookRecyclerView.layoutManager = LinearLayoutManager(this@PhoneBookWithRecyclerViewActivity)

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
    val inflater: LayoutInflater
) : RecyclerView.Adapter<PhoneBookRecyclerAdapter.ViewHolder>() {

    inner class ViewHolder(val itemView_binding: PhonebookItemBinding) :
        RecyclerView.ViewHolder(itemView_binding.root) {
        val personName: TextView

        init {
            personName = itemView_binding.personName
            itemView_binding.root.setOnClickListener {
                val position: Int = adapterPosition
                val name = itemList.personList[position].name
                Log.d("name", name)
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