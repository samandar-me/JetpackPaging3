package com.example.pagingthree.network

import com.example.pagingthree.model.RickAndMortyList
import com.example.pagingthree.util.Constants.END_POINT
import retrofit2.http.GET
import retrofit2.http.Query

interface RetroService {

    @GET(END_POINT)
    suspend fun getDataFromApi(@Query("page") query: Int): RickAndMortyList
}