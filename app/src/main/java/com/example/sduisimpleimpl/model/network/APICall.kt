package com.example.sduisimpleimpl.model.network

import com.example.sduisimpleimpl.model.data.SDUIModel
import com.example.sduisimpleimpl.utils.Constants
import retrofit2.Response
import retrofit2.http.GET

interface APICall
{
    @GET(Constants.GET_ENDPOINT)
    suspend fun getData(): Response<SDUIModel>
}