package com.wooyj.alicorn.ui.pages

import androidx.lifecycle.*
import com.google.gson.Gson
import com.wooyj.alicorn.data.model.ModelChatContent
import com.wooyj.alicorn.data.model.ModelChatList
import com.wooyj.alicorn.data.model.ModelUser
import com.wooyj.alicorn.data.model.Resource
import com.wooyj.alicorn.data.preference.UserDataStore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatDetailViewModel @Inject constructor(
    userDataStore: UserDataStore
) : ViewModel() {

    private val refreshIntervalMs: Long = 1000
    lateinit var model : ModelUser

    val res: LiveData<Resource<MutableList<ModelChatContent>>>
        get() = _res
    private val _res = getContentList().asLiveData()

    init{
        viewModelScope.launch {
            model = Gson().fromJson(userDataStore.userString.first(), ModelUser::class.java)
        }
    }

    private var list = mutableListOf(
        ModelChatContent(
            userId = "test",
            content = "1번 테스트",
            time = System.currentTimeMillis(),
            isMy = true
        ),
        ModelChatContent(
            userId = "d",
            content = "2번 테스트",
            time = System.currentTimeMillis(),
            isMy = false
        ),
        ModelChatContent(
            userId = "d",
            content = "3번 테스트",
            time = System.currentTimeMillis(),
            isMy = true
        ),
        ModelChatContent(
            userId = "test",
            content = "4번 테스트",
            time = System.currentTimeMillis(),
            isMy = false
        )
    )


    private fun getContentList() = flow {
        while (true) {
            emit(
                Resource.success(
                    list
                )
            )
            delay(refreshIntervalMs)
        }
    }

    fun sendMessage(model:ModelChatContent){
        //TODO("서버와 통신하기")
        list.add(model)
    }

}