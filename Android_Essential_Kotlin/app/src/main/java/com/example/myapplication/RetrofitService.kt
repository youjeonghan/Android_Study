package com.example.myapplication

import retrofit2.Call
import retrofit2.http.*

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

    // 객체를 보낼때는 @POST만 써도 되지만 필드를 보낼때는 @FormUrlEncoded 도 적어줘야한다
    @POST("user/signup/")
    @FormUrlEncoded
    fun register(
        // 서버에서 객체로 받을때
//        @Body register: Register
        @Field("username") username: String,
        @Field("password1") password1: String,
        @Field("password2") password2: String
    ): Call<User>

    @POST("user/login/")
    @FormUrlEncoded
    fun login(
        @Field("username") username: String,
        @Field("password") password: String
    ): Call<User>
}