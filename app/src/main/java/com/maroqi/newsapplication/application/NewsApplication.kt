package com.maroqi.newsapplication.application

import android.app.Application
import androidx.room.Room
import com.maroqi.newsapplication.application.factory.NewsViewModelFactory
import com.maroqi.newsapplication.application.usecases.GetBookmarks
import com.maroqi.newsapplication.application.usecases.GetEverything
import com.maroqi.newsapplication.application.usecases.InsertBookmark
import com.maroqi.newsapplication.application.usecases.UseCases
import com.maroqi.newsapplication.domain.apiservices.retrofit.EverythingApiService
import com.maroqi.newsapplication.domain.apiservices.retrofit.TopHeadlinesApiService
import com.maroqi.newsapplication.infrastructure.db.room.RoomDb
import com.maroqi.newsapplication.infrastructure.network.retorfit.RetrofitNetwork

class NewsApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        val client = RetrofitNetwork().client
        val everythingApiService = client.create(EverythingApiService::class.java)
        val topHeadlinesApiService = client.create(TopHeadlinesApiService::class.java)

        val db =
            Room.databaseBuilder(applicationContext, RoomDb::class.java, "news_database").build()

        NewsViewModelFactory.inject(
            UseCases(
                GetEverything(everythingApiService),
                GetBookmarks(db.newsRepository()),
                InsertBookmark(db.newsRepository())
            )
        )
    }
}