package com.example.sduisimpleimpl.di

import com.example.sduisimpleimpl.model.data.SDUIModel
import com.example.sduisimpleimpl.model.network.APICall
import com.example.sduisimpleimpl.model.repository.Repository
import com.example.sduisimpleimpl.model.repository.RepositoryImpl
import com.example.sduisimpleimpl.utils.Constants
import com.example.sduisimpleimpl.utils.UIComponentDeserializer
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule
{

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return GsonBuilder()
            .registerTypeAdapter(SDUIModel.UIComponent::class.java, UIComponentDeserializer())
            .create()
    }


    @Provides
    @Singleton
    fun provideRetrofit(gson: Gson): Retrofit
    {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    @Provides
    @Singleton
    fun provideAPI(retrofit: Retrofit): APICall
    {
        return retrofit.create(APICall::class.java)

    }

    @Provides
    fun providesRepository(apiCall: APICall): Repository
    {
        return RepositoryImpl(apiCall)
    }


}