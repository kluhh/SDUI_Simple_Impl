package com.example.sduisimpleimpl.model.repository

import android.util.Log
import com.example.sduisimpleimpl.model.data.SDUIModel
import com.example.sduisimpleimpl.model.network.APICall
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class RepositoryImpl @Inject constructor(private val apiCall: APICall) : Repository
{
    val TAG = "repository"
    override suspend fun getData(): kotlinx.coroutines.flow.Flow<SDUIModel> = flow {
        val response = apiCall.getData()
        if (response.isSuccessful)
        {
            response.body()?.let {
                Log.d(TAG, "Data: $it")
                emit(it)
            }?: throw Exception("No data found")

            //            Log.d("repository","$response")
            //            emit(response.body() ?: throw Exception("No data found"))
        }
        else
        {
            Log.d(TAG, "Error: ${response.errorBody()?.string()}")
            throw Exception("Error fetching data")
        }
    }

}
