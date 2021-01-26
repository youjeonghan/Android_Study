package com.example.myapplication

import android.app.Activity
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
    lateinit var registerBtn: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEmailSignupBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
        setupListener()

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

    fun setupListener() {
        binding.register.setOnClickListener {
            register(this@EmailSignupActivity)
        }
    }

    fun register(activity: Activity) {
        val username = usernameView.text.toString()
        val password1 = userPassword1View.text.toString()
        val password2 = userPassword2View.text.toString()
        val register = Register(username, password1, password2)

        (application as MasterApplication).service.register(register)
            .enqueue(object : Callback<User> {
                override fun onResponse(call: Call<User>, response: Response<User>) {
                    if (response.isSuccessful) {
                        Toast.makeText(activity, "가입에 성공 하였습니다.", Toast.LENGTH_SHORT).show()
                        val user = response.body()
                        val token = user!!.token!!
                        saveUserToken(token, activity)
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