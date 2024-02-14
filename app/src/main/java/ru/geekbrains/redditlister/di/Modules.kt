package ru.geekbrains.redditlister.di

import ru.geekbrains.redditlister.api.RedditApi
import ru.geekbrains.redditlister.db.RedditDb
import ru.geekbrains.redditlister.domain.DbRedditPostRepository
import ru.geekbrains.redditlister.domain.Repository
import ru.geekbrains.redditlister.ui.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { RedditApi.create() }
    single { RedditDb.create(get(), false) }
    single<Repository> { DbRedditPostRepository(get(), get()) }
    viewModel { MainViewModel(get()) }
}