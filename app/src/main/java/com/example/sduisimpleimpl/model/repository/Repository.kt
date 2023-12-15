package com.example.sduisimpleimpl.model.repository

import com.example.sduisimpleimpl.model.data.SDUIModel
import kotlinx.coroutines.flow.Flow

interface Repository
{
    suspend fun getData(): Flow<SDUIModel>
}