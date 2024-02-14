package ru.geekbrains.redditlister.domain

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import ru.geekbrains.redditlister.api.RedditApi
import ru.geekbrains.redditlister.data.RedditPost
import kotlinx.coroutines.flow.Flow

class RemoteRepository(
    private val redditApi: RedditApi
) : Repository {
    override fun getRedditData(pageSize: Int): Flow<PagingData<RedditPost>> =
        Pager(config = PagingConfig(
            pageSize = pageSize,
            enablePlaceholders = false,
            initialLoadSize = 20
        ), pagingSourceFactory = { DataPagingSource(redditApi) }
        ).flow
}