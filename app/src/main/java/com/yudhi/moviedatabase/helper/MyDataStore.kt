package com.yudhi.moviedatabase.helper

import android.accounts.AccountManager.KEY_PASSWORD
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


class MyDataStore(val context: Context) {
    private val Context.dataStore by preferencesDataStore(name = "user_prefs")
    companion object {
        val FOTO_PROFILE = stringPreferencesKey("foto_profile")
        val KEY_USERNAME = stringPreferencesKey("username")
        val KEY_PASSWORD = stringPreferencesKey("password")
        val EMAIL_PASSWORD = stringPreferencesKey("email")
        val IS_LOGIN_KEY = booleanPreferencesKey("is_login_key")
    }


    suspend fun saveAccount(username: String, password: String, email: String) {
        context.dataStore.edit { preferences ->
            preferences[KEY_USERNAME] = username
            preferences[KEY_PASSWORD] = password
            preferences[EMAIL_PASSWORD] = email
            preferences[IS_LOGIN_KEY] = true
        }
    }

    fun getSavedAccount(): Flow<Triple<String?, String?, String?>> {
        return context.dataStore.data.map { preferences ->
            val username = preferences[KEY_USERNAME]
            val password = preferences[KEY_PASSWORD]
            val email = preferences[EMAIL_PASSWORD]
            Triple(username, password, email)
        }
    }

    suspend fun saveFoto(foto: String) {
        context.dataStore.edit { preferences ->
            preferences[FOTO_PROFILE] = foto
            Toast.makeText(context, "SAVED", Toast.LENGTH_SHORT).show()
            Log.d("d", "cantt")
        }
    }

    fun getFoto(): Flow<String?> {
        return context.dataStore.data.map { preferences ->
            preferences[FOTO_PROFILE]
        }
    }


    suspend fun clearAccount() {
        context.dataStore.edit { preferences ->
            preferences.remove(KEY_USERNAME)
            preferences.remove(KEY_PASSWORD)
            preferences.remove(EMAIL_PASSWORD)
            preferences.remove(FOTO_PROFILE)
            preferences[IS_LOGIN_KEY] = false
        }
    }

    fun getLogin(): Flow<Boolean> {
        return context.dataStore.data.map {preferences ->
            preferences[IS_LOGIN_KEY] ?: false
        }
    }



}
