package com.example.mmason_lab4

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mmason_lab4.model.User
import com.example.mmason_lab4.network.UserService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class UserViewModel : ViewModel() {

    private val _users = MutableStateFlow<List<User>?>(null)
    val users: StateFlow<List<User>?> = _users

    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    //  Custom client with timeout handling
    private val client = OkHttpClient.Builder()
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .build()

    private val userService = Retrofit.Builder()
        .baseUrl("https://randomuser.me/")
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(UserService::class.java)

    init {
        fetchUsers()
    }

    private fun fetchUsers() {
        viewModelScope.launch {
            _isLoading.value = true
            var retries = 3

            while (retries > 0) {
                try {
                    val result = userService.getUsers()
                    _users.value = result.results
                    _error.value = null
                    break //  success, break out of retry loop
                } catch (e: Exception) {
                    retries--
                    if (retries == 0) {
                        _error.value = "Failed to fetch users: ${e.message}"
                    }
                }
            }
            _isLoading.value = false
        }
    }

}
