package com.melendez.paulo.laboratoriocalificado03
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("list/teacher")
    fun getTeachers(): Call<List<Teacher>>
}
