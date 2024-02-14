package ru.geekbrains.redditlister.data

class ListingData(
    val children: List<RedditChildrenResponse>,
    val after: String?,
    val before: String?
)