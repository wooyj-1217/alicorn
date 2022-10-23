package com.wooyj.alicorn.ui.pages

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.google.gson.Gson
import com.wooyj.alicorn.data.model.ModelChatList
import com.wooyj.alicorn.data.model.ModelUser
import com.wooyj.alicorn.data.model.Resource
import com.wooyj.alicorn.data.preference.UserDataStore
import com.wooyj.alicorn.data.repository.ChatRepository
import com.wooyj.alicorn.data.repository.fake.FakeChatRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@HiltViewModel
class ChatListViewModel @Inject constructor(
    userDataStore: UserDataStore,
    chatRepository: ChatRepository
) : ViewModel() {

    val res: LiveData<Resource<List<ModelChatList>?>>
        get() = _res
    private val _res = userDataStore.userString.flatMapLatest { str ->
        val model = Gson().fromJson(str, ModelUser::class.java)
        // TODO("로그인된 user 데이터를 바탕으로 서버 데이터 가져오기. 현재는 fake data")
        chatRepository.getChatListStream(model.id)
    }.asLiveData()


}