package com.maroqi.newsapplication.modules.bookmark.application.usecases

import com.maroqi.newsapplication.core.application.usecases.UseCase
import com.maroqi.newsapplication.modules.news.domain.models.NewsModel
import com.maroqi.newsapplication.core.network.Request
import com.maroqi.newsapplication.modules.news.domain.dao.room.NewsDao
import java.lang.Exception

class UpdateBookmark(private val dao: NewsDao) : UseCase<NewsModel>() {
    override suspend fun execute(request: Request<NewsModel>) {
        try {
            val news = request.data
            if (news != null) {
                if (news.isBookmarked) {
                    InsertBookmark(dao)(request)
                } else {
                    DeleteBookmark(dao)(request)
                }
            }
        } catch (e: Exception) {
            request.onFailure(e.localizedMessage ?: "")
        }
    }
}
