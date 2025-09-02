package com.app.amerodeh.falconssoftandroidjopexamcleanarchitecture.data.api

import com.app.amerodeh.falconssoftandroidjopexamcleanarchitecture.data.model.ApiResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ItemService {
    @GET("getvanalldata")
    suspend fun getSalesManBalance(
        @Query("CONO") cono: Int,
        @Query("STRNO") strno: Int,
        @Query("CASE") caseId: Int = 9 //constant
    ): Response<ApiResponse>

    @GET("getvanalldata")
    suspend fun getItemsMaster(
        @Query("CONO") cono: Int,
        @Query("STRNO") strno: Int,
        @Query("CASE") caseId: Int = 4 // constant
    ): Response<ApiResponse>
}
