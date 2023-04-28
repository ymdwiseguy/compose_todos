package com.ymdwiseguy.todos.repo

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.MutablePreferences
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.squareup.moshi.Moshi
import com.ymdwiseguy.todos.domain.Todo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TodosDataStore(
    private val dataStore: DataStore<Preferences>,
    moshi: Moshi
) {

    private val adapter = moshi.adapter(Todo::class.java)

    suspend fun write(todo: Todo) {
        dataStore.edit { preferences ->
            preferences[stringPreferencesKey(todo.uuid)] = adapter.toJson(todo)
        }
    }

    fun todosFlow(): Flow<List<Todo>> = dataStore.data.map { preferences ->
        preferences.asMap().mapNotNull {
            val value = it.value
            if (value is String) {
                try {
                    adapter.fromJson(value)
                } catch (e: Exception) {
                    // TODO: remove corrupted data
                    Log.w("TodosSharedPrefs", "Corrupted Todo: $value")
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

}