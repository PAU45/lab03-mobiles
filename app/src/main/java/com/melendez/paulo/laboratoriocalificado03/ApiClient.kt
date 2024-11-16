package com.melendez.paulo.laboratoriocalificado03

import retrofit2.Response
import retrofit2.http.GET

interface ApiClient {
    @GET("list/teacher")
    suspend fun getTeachers(): Response<ProfesorListResponse>
}


