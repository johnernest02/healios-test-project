package com.healiostestproject.android.application

import com.healiostestproject.android.backend.DataProvider
import com.healiostestproject.android.backend.HealiosDataProvider
import com.healiostestproject.android.backend.LocalFramework
import com.healiostestproject.android.backend.RestFramework
import com.healiostestproject.android.backend.retrofit.RetrofitRestFramework
import com.healiostestproject.android.backend.room.RoomLocalFramework
import com.healiostestproject.android.screens.details.DetailsActivity
import com.healiostestproject.android.screens.details.DetailsViewModel
import com.healiostestproject.android.screens.posts.PostsActivity
import com.healiostestproject.android.screens.posts.PostsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val appModule = module {

    scope(named<PostsActivity>()) {
        viewModel { PostsViewModel(get()) }
    }

    scope(named<DetailsActivity>()) {
        viewModel { DetailsViewModel(get()) }
    }
}

val backendModule = module {
    single<LocalFramework> { RoomLocalFramework(get()) }

    single<RestFramework> { RetrofitRestFramework(get()) }

    single<DataProvider> { HealiosDataProvider(get(), get(), get()) }

}