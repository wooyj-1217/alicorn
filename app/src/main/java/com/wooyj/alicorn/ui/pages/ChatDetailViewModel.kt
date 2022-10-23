package com.wooyj.alicorn.ui.pages

import androidx.lifecycle.*
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.wooyj.alicorn.BuildConfig
import com.wooyj.alicorn.data.model.ModelChatContent
import com.wooyj.alicorn.data.model.ModelUser
import com.wooyj.alicorn.data.model.Resource
import com.wooyj.alicorn.data.preference.UserDataStore
import com.wooyj.alicorn.data.repository.ChatRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.socket.client.IO
import io.socket.client.Socket
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatDetailViewModel @Inject constructor(
    private var chatRepository: ChatRepository,
    savedStateHandle: SavedStateHandle,
    userDataStore: UserDataStore
) : ViewModel() {

    lateinit var model: ModelUser

    lateinit var socket: Socket

    val chatId = MutableStateFlow(
        savedStateHandle.get<Long>("chatId") ?: 0L
    )

    /**
     *
     * ui 확인을 위한 fake data. socket 통신 성공 시 없애면 됨.
     *
     */
    val res: LiveData<Resource<List<ModelChatContent>?>>
        get() = _res
    private val _res = chatId.flatMapLatest {
        chatRepository.getChatContent(it)
    }.asLiveData()

    /**
     *
     * socket 서버가 있다고 가정하고 작성해본 코드며,
     * 보여지는 UI는 현재 fake data.
     *
     */
    init {

        viewModelScope.launch {
            model = Gson().fromJson(userDataStore.userString.first(), ModelUser::class.java)

            socket = IO.socket("${BuildConfig.CHAT_URL}/${chatId}").also {
                it.connect()
            }

            socket.on(Socket.EVENT_CONNECT) {
                //연결에 성공했을 때
                //TODO("소켓 서버로부터 이전 list 받아오기")
            }.on("msg") {
                val obj = it[0] as JsonObject
                val data = Gson().fromJson(obj, ModelChatContent::class.java)
                list.add(data)
            }.on(Socket.EVENT_CONNECT_ERROR) {
                //연결에 오류가 발생했을 경우
                socket.disconnect()
                //TODO("status data 전달")
            }.on(Socket.EVENT_DISCONNECT) {
                //연결 끊어졌을 경우
                //TODO("status data 전달")
            }

        }

    }

    private var list = mutableListOf<ModelChatContent>()

    fun sendMessage(model: ModelChatContent) {
//        list.add(model)
        socket.emit("msg", Gson().toJson(model))
    }

    override fun onCleared() {
        super.onCleared()
        if (socket != null) {
            socket.disconnect()
        }
    }

}