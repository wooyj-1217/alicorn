package com.wooyj.alicorn.ui.pages

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.wooyj.alicorn.data.model.ModelUser
import com.wooyj.alicorn.data.preference.UserDataStore
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val userDataStore: UserDataStore
) : ViewModel() {

    private var _valid: MutableLiveData<Boolean> = MutableLiveData()
    val valid: LiveData<Boolean>
        get() = _valid

    private var _result: MutableLiveData<ModelUser> = MutableLiveData()
    val result: LiveData<ModelUser>
        get() = _result

    //서버 없이 붙이는 용도.
    private var isSuccess = true

    fun validationIdCheck(userId: String) {
        //TODO("서버 데이터 연결 결과값 받아오기")
        _valid.postValue(true)
    }

    suspend fun signIn(model: ModelUser) {
        //TODO("서버에 데이터 보낸 후 결과값 받아오기")
        if (isSuccess) {
            //회원가입 성공했을 경우
            model.pw = ""
            userDataStore.setUser(Gson().toJson(model))
            _result.postValue(model)
        } else {
            //실패했을 경우
            _result.postValue(ModelUser("", "", "", "", "", ""))
        }
    }

}