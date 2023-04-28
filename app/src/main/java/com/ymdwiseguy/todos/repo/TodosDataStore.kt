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

class TodosDataStore(
    private val dataStore: DataStore<Preferences>,
    private val dispatcher: CoroutineDispatcher,
    moshi: Moshi
) {

    private val adapter = moshi.adapter(Todo::class.java)

    suspend fun write(todo: Todo) {
        dataStore.edit { preferences ->
            preferences[stringPreferencesKey(todo.uuid)] = adapter.toJson(todo)
        }
    }

    fun todosFlow(): Flow<List<Todo>> = dataStore.data.map { preferences ->
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

    suspend fun remove(todo: Todo) {
        dataStore.edit { preferences ->
            preferences.remove(stringPreferencesKey(todo.uuid))
        }
    }

    suspend fun clear() {
        dataStore.edit(MutablePreferences::clear)
    }

    suspend fun updateTodo(todo: Todo) = dataStore.edit { preferences ->
        preferences[stringPreferencesKey(todo.uuid)] = adapter.toJson(todo)
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