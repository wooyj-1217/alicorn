package com.wooyj.alicorn.data.preference

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.longPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.google.gson.Gson
import com.wooyj.alicorn.data.model.ModelUser
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

class UserDataStore(private val context: Context) {
    private val Context.dataStore  by preferencesDataStore(name = "dataStore")

    private val user = stringPreferencesKey("user")

    val userString : Flow<String> = context.dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        .map {preferences ->
            preferences[user] ?: Gson().toJson(ModelUser("","","","",""))
        }

    // String값을 stringKey 키 값에 저장
    suspend fun setUser(userString : String){
        context.dataStore.edit { preferences ->
            preferences[user] = userString
        }
    }

}