package com.ymdwiseguy.todos.repo

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.MutablePreferences
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.squareup.moshi.Moshi
import com.ymdwiseguy.todos.domain.Todo
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

interface TodosDataStoreInterface{
    fun todosFlow(): Flow<List<Todo>>

    suspend fun write(todo: Todo)
    suspend fun remove(todo: Todo)
    suspend fun clear()
}

class TodosDataStore(
    private val dataStore: DataStore<Preferences>,
    private val dispatcher: CoroutineDispatcher,
    moshi: Moshi
) : TodosDataStoreInterface {

    private val adapter = moshi.adapter(Todo::class.java)

    override suspend fun write(todo: Todo) {
        dataStore.edit { preferences ->
            preferences[stringPreferencesKey(todo.uuid)] = adapter.toJson(todo)
        }
    }

    override fun todosFlow(): Flow<List<Todo>> = dataStore.data.map { preferences ->
        preferences.asMap().mapNotNull { entry ->
            val value = entry.value
            if (value is String) {
                try {
                    adapter.fromJson(value)
                } catch (e: Exception) {
                    deleteCorruptedEntry(entry)
                    null
                }
            } else null
        }
    }

    override suspend fun remove(todo: Todo) {
        dataStore.edit { preferences ->
            preferences.remove(stringPreferencesKey(todo.uuid))
        }
    }

    override suspend fun clear() {
        dataStore.edit(MutablePreferences::clear)
    }

    private suspend fun deleteCorruptedEntry(entry: Map.Entry<Preferences.Key<*>, Any>) = withContext(dispatcher) {
        Log.w("TodosDataStore", "Deleting Corrupted Data: ${entry.value}")

        launch {
            dataStore.edit { preferences ->
                preferences.remove(entry.key)
            }
        }
    }
}