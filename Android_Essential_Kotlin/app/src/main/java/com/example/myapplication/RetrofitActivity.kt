package com.example.myapplication

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivityRetrofitBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRetrofitBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRetrofitBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // http://mellowcode.org/json/students/
        // http://mellowcode.org/test/code/
        val retrofit = Retrofit.Builder()
            .baseUrl("http://mellowcode.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(RetrofitService::class.java)

        // GET 요청
        // enqueue를 통해서 결과값을 Callback으로 넘겨받는다
        service.getStudentsList().enqueue(object : Callback<ArrayList<PersonFromServer>> {
            // 통신 성공시 호출
            override fun onResponse(
                call: Call<ArrayList<PersonFromServer>>,
                response: Response<ArrayList<PersonFromServer>>
            ) {
                if (response.isSuccessful) {
                    val personList = response.body()
                    Log.d("retrofitt", "res : " + personList?.get(0)?.age)

                    val code = response.code()
                    Log.d("retrofitt", "code : " + code)

                    val error = response.errorBody()
                    val header = response.headers()
                    Log.d("retrofitt", "header : " + header)
                }
            }

            // 통신 실패시 호출
            override fun onFailure(call: Call<ArrayList<PersonFromServer>>, t: Throwable) {
                Log.d("retrofitt", "ERROR")
            }
        })

        // POST 요청 (1)
//        val params = HashMap<String, Any>()
//        params.put("name", "유정한")
//        params.put("age", 20)
//        params.put("intro", "안녕하세요")
//        service.createStudents(params).enqueue(object : Callback<PersonFromServer> {
//            override fun onResponse(
//                call: Call<PersonFromServer>,
//                response: Response<PersonFromServer>
//            ) {
//                if (response.isSuccessful) {
//                    val person = response.body()
//                    Log.d("retrofitt", "name : " + person?.name)
//                }
//            }
//
//            override fun onFailure(call: Call<PersonFromServer>, t: Throwable) {
//                TODO("Not yet implemented")
//            }
//        })

        // POST 요청 (2)
        val person = PersonFromServer(name = "유정한", age = 26, intro = "안녕")
        service.createStudentsEasy(person).enqueue(object : Callback<PersonFromServer> {
            override fun onResponse(
                call: Call<PersonFromServer>,
                response: Response<PersonFromServer>
            ) {
                if (response.isSuccessful) {
                    val person = response.body()
                    Log.d("retrofitt", "name : " + person?.name)
                }
            }

            override fun onFailure(call: Call<PersonFromServer>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }
}