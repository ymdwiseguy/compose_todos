package com.ymdwiseguy.todos

import android.app.Application
import android.content.Context.MODE_PRIVATE
import androidx.datastore.core.handlers.ReplaceFileCorruptionHandler
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStoreFile
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.ymdwiseguy.todos.repo.CredentialsRepository
import com.ymdwiseguy.todos.repo.CredentialsSharedPrefs
import com.ymdwiseguy.todos.repo.TodosApi
import com.ymdwiseguy.todos.repo.TodosRepository
import com.ymdwiseguy.todos.repo.TodosDataStore
import com.ymdwiseguy.todos.repo.adapter.OffsetDateTimeAdapter
import com.ymdwiseguy.todos.viewmodel.LoginViewModel
import com.ymdwiseguy.todos.viewmodel.StartViewModel
import com.ymdwiseguy.todos.viewmodel.TodosViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module
import retrofit2.Retrofit

private const val CREDENTIALS_SHARED_PREFS_NAME = "CredentialsSharedPreferences"
private const val TODOS_DATA_STORE_NAME = "TodosDataStore"

val todosModule = module {

    single {
        Moshi
            .Builder()
            .add(OffsetDateTimeAdapter())
            .addLast(KotlinJsonAdapterFactory())
            .build()
    }

    single {
        NetworkConfiguration.buildRetrofit(
            credentialsRepository = get(),
            moshi = get(),
        )
    }

    single {
        CredentialsSharedPrefs(
            sharedPreferences = get<Application>().getSharedPreferences(
                CREDENTIALS_SHARED_PREFS_NAME, MODE_PRIVATE
            ),
            moshi = get(),
        )
    }

    single {
        TodosDataStore(
            dataStore = PreferenceDataStoreFactory.create(
                corruptionHandler = ReplaceFileCorruptionHandler { emptyPreferences() },
                migrations = listOf(),
                produceFile = {
                    get<Application>()
                        .applicationContext
                        .preferencesDataStoreFile(TODOS_DATA_STORE_NAME)
                },
            ),
            moshi = get(),
        )
    }

    factory { (get<Retrofit>().create(TodosApi::class.java)) }

    factoryOf(::CredentialsRepository)
    factoryOf(::TodosRepository)

    viewModelOf(::StartViewModel)
    viewModelOf(::TodosViewModel)
    viewModelOf(::LoginViewModel)

}