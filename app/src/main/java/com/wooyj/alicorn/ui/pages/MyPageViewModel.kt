package com.wooyj.alicorn.ui.pages

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.wooyj.alicorn.data.model.ModelUser
import com.wooyj.alicorn.data.preference.UserDataStore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyPageViewModel @Inject constructor(
    private val userDataStore: UserDataStore
) : ViewModel() {

    private val _loggedIn = userDataStore.userString.flatMapLatest { str ->
        flow {
            var model = Gson().fromJson(str, ModelUser::class.java)
            emit(model)
        }
    }.asLiveData()
    val loggedIn: LiveData<ModelUser>
        get() = _loggedIn

    fun logOut(){
        viewModelScope.launch {
            //TODO("로그아웃 처리")
            userDataStore.setUser(Gson().toJson(ModelUser("", "", "", "", "","")))
        }
    }


}