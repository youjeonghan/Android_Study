package com.example.myapplication

import retrofit2.Call
import retrofit2.http.GET

interface RetrofitService {

    // "json/students/" 여기에서 response가 ArrayList<PersonFromServer>이다 란 말
    @GET("json/students/")
    fun getStudentsList(): Call<ArrayList<PersonFromServer>>
}