package com.example.nutriplan.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.example.nutriplan.api.ApiConfig
import com.example.nutriplan.data.AuthDataStore
import com.example.nutriplan.repository.Repository

val Context.dataStore: DataStore<Preferences> by preferencesDataStore("preferences")

object Injection {
    fun provideRepository(context: Context): Repository {
        val api = ApiConfig.getApiService()
        return Repository.getInstance(api, AuthDataStore.getInstance(context.dataStore))
    }
}