package com.example.myapplication

import android.app.Application
import android.content.Context
import com.facebook.stetho.Stetho
import com.facebook.stetho.okhttp3.StethoInterceptor
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


// 액티비티보다 어플레이션 계층이 한단 위임 (oncreate 또한)
// Manifest에 등록 해줘야함 android:name=".MasterApplication"
class MasterApplication : Application() {

    lateinit var service: RetrofitService

    override fun onCreate() {
        super.onCreate()
        Stetho.initializeWithDefaults(this@MasterApplication)
        createRetrofit()
        // chrome://inspect/#devices
    }


    fun createRetrofit() {
        // 원래 나갈려던걸 붙잡고(인터셉트) 헤더를 붙여서 다시 내보내는 것(프로시드 = 다시 진행행)
        val header = Interceptor {
            val original = it.request()


            if (checkIsLogin()) {
                getUserToken()?.let { token ->
                    val request = original.newBuilder()
                        .header("Authorization", "token " + token)
                        .build()
                    it.proceed(request)
                }
            } else {
                it.proceed(original)
            }
        }

        // client 안에 위의 헤더가 들어있다
        val client = OkHttpClient.Builder()
            .addInterceptor(header)
            .addNetworkInterceptor(StethoInterceptor())
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("http://mellowcode.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
        service = retrofit.create(RetrofitService::class.java)
    }

    fun checkIsLogin(): Boolean {
        val sp = getSharedPreferences("login_sp", Context.MODE_PRIVATE)
        val token = sp.getString("login_sp", "null")
        if (token != "null") return true
        else return false
    }

    fun getUserToken(): String? {
        val sp = getSharedPreferences("login_sp", Context.MODE_PRIVATE)
        val token = sp.getString("login_sp", "null")
        if (token == "null") return null
        else return token
    }
}