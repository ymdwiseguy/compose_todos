package com.ymdwiseguy.todos

import android.app.Application
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.ymdwiseguy.todos.NetworkConfiguration.buildRetrofit
import com.ymdwiseguy.todos.repo.TodosApi
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.context.startKoin
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module
import retrofit2.Retrofit

class TodosApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@TodosApplication)
            modules(
                listOf(
                    todosModule,
                )
            )
        }
    }

}

val todosModule = module {

    single {
        Moshi.Builder().addLast(KotlinJsonAdapterFactory()).build()
    }

    single {
        buildRetrofit(get())
    }

    factory { (get<Retrofit>().create(TodosApi::class.java)) }

    viewModelOf(::TodosViewModel)

    factoryOf(::TodosRepository)
}

