package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.myapplication.databinding.ActivityPhoneBookBinding
import com.example.myapplication.databinding.ItemViewBinding
import com.example.myapplication.databinding.PhonebookItemBinding

class PhoneBookActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPhoneBookBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPhoneBookBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val phoneBook = createFakePhoneBook()
        createPhoneBookList(phoneBook)

    }

    fun createFakePhoneBook(fakeNumber: Int = 30, phoneBook: PhoneBook = PhoneBook()): PhoneBook {
        for (i in 0 until fakeNumber) {
            phoneBook.addPerson(
                Person(name = "" + i + "번째 사람", number = "" + i + "번째 사람의 전화 번호")
            )
        }
        return phoneBook
    }

    fun createPhoneBookList(phoneBook: PhoneBook) {
//        val inflater = LayoutInflater.from(this)
        val container = binding.phonebookListContainer
        for (i in 0 until phoneBook.personList.size) {
            val item_binding = PhonebookItemBinding.inflate(layoutInflater)
            val person_name = item_binding.personName

            person_name.setText(phoneBook.personList[i].name)
            addSetOnClickListener(phoneBook.personList[i], item_binding.root)
            container.addView(item_binding.root)
        }
    }
    
    // 리스너 장착 함수
    fun addSetOnClickListener(person: Person, view: View) {
        view.setOnClickListener{
            val intent = Intent(this@PhoneBookActivity, PhoneBookDetailActivity::class.java)
            intent.putExtra("name", person.name)
            intent.putExtra("number", person.number)
            startActivity(intent)
        }
    }


}

class PhoneBook() {
    // 전화 번호부
    val personList = ArrayList<Person>()

    fun addPerson(person: Person) {
        personList.add(person)
    }
}

class Person(val name: String, var number: String) {

}