package com.wooyj.alicorn.ui.pages

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.wooyj.alicorn.data.model.ModelUser
import com.wooyj.alicorn.data.preference.UserDataStore
import com.wooyj.alicorn.data.repository.AuthRepository
import com.wooyj.alicorn.data.repository.fake.FakeAuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val userDataStore: UserDataStore
) : ViewModel() {

    fun login(id: String, pw: String) = authRepository.loginStream(id, pw)

    fun saveUserData(userString: String) = viewModelScope.launch {
        userDataStore.setUser(userString)
    }
}