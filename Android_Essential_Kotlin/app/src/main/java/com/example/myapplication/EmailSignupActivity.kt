package com.example.myapplication

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.myapplication.databinding.ActivityEmailSignupBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EmailSignupActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEmailSignupBinding
    lateinit var usernameView: EditText
    lateinit var userPassword1View: EditText
    lateinit var userPassword2View: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEmailSignupBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
        setupListener(this@EmailSignupActivity)
    }

    fun saveUserToken(token: String, activity: Activity) {
        val sp = activity.getSharedPreferences("login_sp", Context.MODE_PRIVATE)
        val editor = sp.edit()
        editor.putString("login_sp", token)
        editor.commit()
    }

    fun initView() {
        usernameView = binding.usernameInputbox
        userPassword1View = binding.password1Inputbox
        userPassword2View = binding.password2Inputbox
    }

    fun setupListener(activity: Activity) {
        binding.register.setOnClickListener {
            register(this@EmailSignupActivity)
        }
        binding.login.setOnClickListener {
            // intent 바로 안에서 만들고 시작하기
            startActivity(
                Intent(this@EmailSignupActivity, LoginActivity::class.java)
            )
        }

    }

    fun register(activity: Activity) {
        val username = getUserName()
        val password1 = getUserPassword1()
        val password2 = getUserPassword2()

        // enqueue로 callback을 넘겨받음
        (application as MasterApplication).service.register(username, password1, password2)
            .enqueue(object : Callback<User> {
                override fun onResponse(call: Call<User>, response: Response<User>) {
                    if (response.isSuccessful) {
                        Toast.makeText(activity, "가입에 성공 하였습니다.", Toast.LENGTH_SHORT).show()
                        val user = response.body()
                        val token = user!!.token!!
                        saveUserToken(token, activity)
                        // 처음 가입시 sharedpreferences가 없었을거이기에 다시 호출하여 헤더에 토큰을 넣어줌
                        (application as MasterApplication).createRetrofit()
                    }
                }

                override fun onFailure(call: Call<User>, t: Throwable) {
                    Toast.makeText(activity, "가입에 실패 하였습니다.", Toast.LENGTH_SHORT).show()
                }
            })
    }

    fun getUserName(): String {
        return usernameView.text.toString()
    }

    fun getUserPassword1(): String {
        return userPassword1View.text.toString()
    }

    fun getUserPassword2(): String {
        return userPassword2View.text.toString()
    }

}