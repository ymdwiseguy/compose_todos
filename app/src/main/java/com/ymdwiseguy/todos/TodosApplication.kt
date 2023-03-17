package com.ymdwiseguy.todos

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

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