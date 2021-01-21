package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.myapplication.databinding.ActivityNetworkBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
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

        GlobalScope.launch(Dispatchers.IO) {
            if (connection.responseCode == HttpsURLConnection.HTTP_OK) {
                Log.d("conn", "inputstream : " + connection.inputStream)
                // 버퍼란 묶음으로 읽는다는 뜻
                // 오는 바이트를 UTF-8 방식으로읽고 빨리읽기위해 버퍼리더를 이용
                val reader = BufferedReader(
                    InputStreamReader(
                        connection.inputStream,
                        "UTF-8"
                    )
                )
                buffer = reader.readLine()
                Log.d("conn", "buffer : " + buffer)
            }
            val temp = buffer.split(",")
            Log.d("conn", "bufferin : " + temp)
            Log.d("conn", "bufferin : " + temp[0])
            Log.d("conn", "bufferin : " + temp[1])
            Log.d("conn", "bufferin : " + temp[2])

            val json = Json{ prettyPrint  = true }
            val deptFromJson = json.decodeFromString<Array<PersonFromServer>>(buffer)
            Log.d("conn Json -> Model", "" + deptFromJson[0].age)
        }
    }
}