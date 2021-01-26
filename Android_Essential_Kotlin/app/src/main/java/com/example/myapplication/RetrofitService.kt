package com.example.myapplication

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface RetrofitService {

    // "json/students/" 여기에서 response가 ArrayList<PersonFromServer>이다 란 말
    @GET("json/students/")
    fun getStudentsList(): Call<ArrayList<PersonFromServer>>

    @POST("json/students/")
    fun createStudents(
        @Body params: HashMap<String, Any>
    ): Call<PersonFromServer>

    @POST("json/students/")
    fun createStudentsEasy(
        @Body person: PersonFromServer
    ): Call<PersonFromServer>

    @POST("user/signup/")
    fun register(
        @Body register: Register
    ): Call<User>
}