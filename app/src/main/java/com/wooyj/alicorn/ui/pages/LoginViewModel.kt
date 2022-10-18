package com.wooyj.alicorn.ui.pages

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.wooyj.alicorn.data.model.ModelUser
import com.wooyj.alicorn.data.preference.UserDataStore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val userDataStore: UserDataStore
) : ViewModel() {


    private var _result: MutableLiveData<ModelUser> = MutableLiveData()
    val result: LiveData<ModelUser>
        get() = _result


    fun login(id: String, pw: String) {
        //TODO("login 결과값 서버로부터 가져오기")
        //TODO("로그인 성공 시 user data 저장하기")
        val model = ModelUser("test","","테스트","company","position", "")
        saveUserData(Gson().toJson(model))
        _result.postValue(model)
    }

    private fun saveUserData(userString: String) = viewModelScope.launch {
        userDataStore.setUser(userString)
    }
}