package com.example.nutriplan.data


import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class AuthDataStore private constructor(private val dataStore: DataStore<Preferences>) {
    fun getToken(): Flow<String?> {
        return dataStore.data.map { preferences ->
            preferences[TOKEN_KEY]
        }
    }

    fun getID(): Flow<String?> {
        return dataStore.data.map {
            it[ID_USER]
        }
    }

    suspend fun saveID(id: String) {
        dataStore.edit {
            it[ID_USER] = id
        }
    }

    suspend fun saveToken(token: String) {
        dataStore.edit { preferences ->
            preferences[TOKEN_KEY] = token
        }
    }

    suspend fun clearToken() {
        dataStore.edit { preferences ->
            preferences.remove(TOKEN_KEY)
        }
    }

    suspend fun clearId() {
        dataStore.edit { preferences -> {
                preferences.remove(ID_USER)
            }
        }
    }

    companion object {
        private val TOKEN_KEY = stringPreferencesKey("token")
        private val ID_USER = stringPreferencesKey("id")


        @Volatile
        private var INSTANCE: AuthDataStore? = null
        fun getInstance(dataStore: DataStore<Preferences>): AuthDataStore {
            return INSTANCE ?: synchronized(this) {
                val instance = AuthDataStore(dataStore)
                INSTANCE = instance
                instance
            }
        }
    }
}