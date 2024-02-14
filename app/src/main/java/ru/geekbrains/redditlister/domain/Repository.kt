package ru.geekbrains.redditlister.domain


import androidx.paging.PagingData
import ru.geekbrains.redditlister.data.RedditPost
import kotlinx.coroutines.flow.Flow

interface Repository {
    fun getRedditData(pageSize: Int): Flow<PagingData<RedditPost>>
}