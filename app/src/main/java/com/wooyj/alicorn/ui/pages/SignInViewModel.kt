package com.wooyj.alicorn.ui.pages

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.wooyj.alicorn.data.model.ModelUser
import com.wooyj.alicorn.data.preference.UserDataStore
import com.wooyj.alicorn.data.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val userDataStore: UserDataStore
) : ViewModel() {

    private var _valid: MutableLiveData<Boolean> = MutableLiveData()
    val valid: LiveData<Boolean>
        get() = _valid

    private var _result: MutableLiveData<ModelUser> = MutableLiveData()
    val result: LiveData<ModelUser>
        get() = _result

    //서버 없이 붙이는 용도.

    fun validationIdCheck(userId: String) {
        //TODO("서버 데이터 연결 결과값 받아오기")
        _valid.postValue(true)
    }

    fun signIn(model: ModelUser) =
        authRepository.signInStream(model.id, model.pw, model.name, model.company, model.position)

}