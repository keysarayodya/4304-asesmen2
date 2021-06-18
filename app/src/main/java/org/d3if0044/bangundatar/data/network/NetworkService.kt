package org.d3if0044.bangundatar.data.network

import retrofit2.Call
import retrofit2.http.GET

interface NetworkService {
    companion object {
        const val UNIVERSITY = "search?country=Indonesia"
    }

    @GET(UNIVERSITY)
    fun getUniversity(): Call<UniversityEntity>
}