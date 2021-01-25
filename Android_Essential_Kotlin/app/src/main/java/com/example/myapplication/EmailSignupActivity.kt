package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import com.example.myapplication.databinding.ActivityEmailSignupBinding

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
    }

    fun initView() {
        usernameView = binding.usernameInputbox
        userPassword1View = binding.password1Inputbox
        userPassword2View = binding.password2Inputbox
    }

    fun getUserName(): String {
        return usernameView.text.toString()
    }
    fun getUserPassword1():String{
        return userPassword1View.text.toString()
    }
    fun getUserPassword2():String{
        return userPassword2View.text.toString()
    }

}