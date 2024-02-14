package ru.geekbrains.redditlister.db

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.geekbrains.redditlister.data.RedditPost

@Dao
interface RedditPostDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(posts: List<RedditPost>)

    @Query("SELECT * FROM posts ORDER BY indexInResponse ASC")
    fun getAllPosts(): PagingSource<Int, RedditPost>

    @Query("DELETE FROM posts")
    suspend fun deleteAll()

    @Query("SELECT MAX(indexInResponse) + 1 FROM posts")
    suspend fun getNextIndex(): Int
}