package dev.trindadedev.thecatapi.services

import dev.trindadedev.thecatapi.models.Cat
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface CatApiService
{
    @GET("images/search")
    suspend fun getCats(
        @Query("limit") limit: Int = 10
    ): List<Cat>
}

object RetrofitInstance
{
    private const val BASE_URL = "https://api.thecatapi.com/v1/"

    val api: CatApiService by lazy ()
    {
        Retrofit.Builder ()
            .baseUrl (BASE_URL)
            .addConverterFactory (GsonConverterFactory.create())
            .build ()
            .create (CatApiService::class.java)
    }
}