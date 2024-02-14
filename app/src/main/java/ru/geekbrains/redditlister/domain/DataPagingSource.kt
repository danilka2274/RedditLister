package ru.geekbrains.redditlister.domain

import androidx.paging.PagingSource
import androidx.paging.PagingSource.LoadParams.*
import androidx.paging.PagingState
import ru.geekbrains.redditlister.api.RedditApi
import ru.geekbrains.redditlister.data.RedditPost
import retrofit2.HttpException
import java.io.IOException

class DataPagingSource(
    private val redditApi: RedditApi
) : PagingSource<String, RedditPost>() {

    override suspend fun load(params: LoadParams<String>): LoadResult<String, RedditPost> {
        return try {
            val data = redditApi.getPopularTop(
                after = if (params is Append) params.key else null,
                before = if (params is Prepend) params.key else null,
                limit = params.loadSize
            ).data
            return LoadResult.Page(
                data = data.children.map { it.data },
                prevKey = data.before,
                nextKey = data.after
            )
        } catch (e: IOException) {
            LoadResult.Error(throwable = e)
        } catch (e: HttpException) {
            LoadResult.Error(throwable = e)
        }
    }

    override fun getRefreshKey(state: PagingState<String, RedditPost>): String? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey
        }
    }
}