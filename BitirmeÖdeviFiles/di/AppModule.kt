package com.example.pupilicabitirmeprojesi.di

import com.example.pupilicabitirmeprojesi.data.datasource.YemeklerDataSource
import com.example.pupilicabitirmeprojesi.data.repo.YemeklerRepository
import com.example.pupilicabitirmeprojesi.retrofit.ApiUtils
import com.example.pupilicabitirmeprojesi.retrofit.YemeklerDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun provideYemeklerRepository(yemeklerDataSource: YemeklerDataSource) : YemeklerRepository{
        return YemeklerRepository(yemeklerDataSource)
    }
    @Provides
    @Singleton
    fun providesYemeklerDataSource(yemeklerDao: YemeklerDao) : YemeklerDataSource{
        return YemeklerDataSource(yemeklerDao)
    }
    @Provides
    @Singleton
    fun provideYemeklerDao () : YemeklerDao {
        return ApiUtils.getYemeklerDao()
    }
}