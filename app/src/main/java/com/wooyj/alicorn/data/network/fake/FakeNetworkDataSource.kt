package com.wooyj.alicorn.data.network.fake

import com.wooyj.alicorn.data.model.ModelChatContent
import com.wooyj.alicorn.data.model.ModelChatList
import com.wooyj.alicorn.data.model.ModelUser
import com.wooyj.alicorn.data.network.NetworkDataSource
import com.wooyj.alicorn.data.network.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 *
 * 가짜 데이터용 네트워크 data source
 * 실제 통신내용 대신에 가짜 데이터를 전달한다.
 *
 */
class FakeNetworkDataSource @Inject constructor(
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : NetworkDataSource {

    override suspend fun login(id: String, pw: String): ModelUser? = withContext(ioDispatcher) {
        FakeDataSource.sampleLogin(id, pw)
    }

    override suspend fun signIn(
        id: String,
        pw: String,
        name: String,
        company: String,
        position: String
    ): Unit = withContext(ioDispatcher) {
        null
    }

    override suspend fun logout(id: String): Unit = withContext(ioDispatcher) {
        null
    }

    override suspend fun findUser(keyword: String): List<ModelUser> = withContext(ioDispatcher) {
        FakeDataSource.findUser(keyword)
    }

    override suspend fun getConnectedUsers(id: String): List<ModelUser>? =
        withContext(ioDispatcher) {
            FakeDataSource.findUser("")
        }

    override suspend fun getChatList(id: String): List<ModelChatList> = withContext(ioDispatcher) {
        FakeDataSource.chatList
    }

    override suspend fun getChatContent(chatId: Long): List<ModelChatContent> =
        withContext(ioDispatcher) {
            FakeDataSource.chatContent
        }

}