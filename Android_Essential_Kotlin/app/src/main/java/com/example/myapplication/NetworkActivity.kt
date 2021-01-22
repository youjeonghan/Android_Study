package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.ActivityNetworkBinding
import com.example.myapplication.databinding.PersonListItemBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import javax.net.ssl.HttpsURLConnection

class NetworkActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNetworkBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNetworkBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val urlString: String = "http://mellowcode.org/json/students/"
        val url: URL = URL(urlString)
        val connection: HttpURLConnection = url.openConnection() as HttpURLConnection

        connection.requestMethod = "GET"
        connection.setRequestProperty("Content-Type", "application/json")

        var buffer = ""

        GlobalScope.launch(Dispatchers.Main) {
            val deptFromJson = withContext(Dispatchers.IO) {
                if (connection.responseCode == HttpsURLConnection.HTTP_OK) {
                    // 버퍼란 묶음으로 읽는다는 뜻
                    // 오는 바이트를 UTF-8 방식으로읽고 빨리읽기위해 버퍼리더를 이용
                    val reader = BufferedReader(
                        InputStreamReader(
                            connection.inputStream,
                            "UTF-8"
                        )
                    )
                    buffer = reader.readLine()
                }
                val json = Json { prettyPrint = true }
                json.decodeFromString<Array<PersonFromServer>>(buffer)
            }
            with(binding.recyclerPerson) {
                this.adapter = PersonAdapter(personList = deptFromJson)
            }
        }
    }
}

class PersonAdapter(
    val personList: Array<PersonFromServer>
) : RecyclerView.Adapter<PersonAdapter.ViewHolder>() {
    inner class ViewHolder(itemView_binding: PersonListItemBinding) :
        RecyclerView.ViewHolder(itemView_binding.root) {
        val name: TextView
        val age: TextView
        val intro: TextView

        init {
            name = itemView_binding.personName
            age = itemView_binding.personAge
            intro = itemView_binding.personIntro
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.person_list_item, parent, false)
        return ViewHolder(PersonListItemBinding.bind(view))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.name.setText(personList.get(position).name)
        holder.age.setText(personList.get(position).age.toString())
        holder.intro.setText(personList.get(position).intro)
    }

    override fun getItemCount(): Int {
        return personList.size
    }
}