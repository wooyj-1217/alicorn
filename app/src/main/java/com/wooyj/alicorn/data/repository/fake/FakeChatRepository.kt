package com.wooyj.alicorn.data.repository.fake

import com.wooyj.alicorn.data.model.ModelChatContent
import com.wooyj.alicorn.data.model.ModelChatList
import com.wooyj.alicorn.data.model.ModelUser
import com.wooyj.alicorn.data.model.Resource
import com.wooyj.alicorn.data.network.di.IoDispatcher
import com.wooyj.alicorn.data.network.fake.FakeNetworkDataSource
import com.wooyj.alicorn.data.repository.ChatRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FakeChatRepository @Inject constructor(
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : ChatRepository {

    override fun getChatListStream(id: String): Flow<Resource<List<ModelChatList>?>> = flow {
        emit(Resource.loading(null))
        emit(Resource.success(FakeNetworkDataSource(ioDispatcher).getChatList(id)))
    }

    override fun getChatContent(chatId: Long): Flow<Resource<List<ModelChatContent>?>> = flow {
        emit(Resource.loading(null))
        emit(Resource.success(FakeNetworkDataSource(ioDispatcher).getChatContent(chatId)))
    }

    override fun findUser(keyword: String): Flow<Resource<List<ModelUser>?>> = flow {
        emit(Resource.loading(null))
        emit(Resource.success(FakeNetworkDataSource(ioDispatcher).findUser(keyword)))
    }

}