package com.maroqi.newsapplication.modules.bookmark.application.usecases

import com.maroqi.newsapplication.core.application.usecases.UseCase
import com.maroqi.newsapplication.modules.news.domain.models.NewsModel
import com.maroqi.newsapplication.core.network.Request
import com.maroqi.newsapplication.modules.news.domain.dao.room.NewsDao
import dagger.hilt.android.scopes.ViewModelScoped
import java.lang.Exception
import javax.inject.Inject

@ViewModelScoped
class DeleteBookmark @Inject constructor(private val dao: NewsDao) : UseCase<NewsModel>() {
    override suspend fun execute(request: Request<NewsModel>) {
        try {
            dao.delete(request.data!!.title!!)
            request.onSuccess(null)
        } catch (e: Exception) {
            request.onFailure(e.localizedMessage ?: "")
        }
    }
}
