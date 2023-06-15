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
        val api2 = ApiConfig.getApiService2()
        return Repository.getInstance(api,api2, AuthDataStore.getInstance(context.dataStore))
    }
}
