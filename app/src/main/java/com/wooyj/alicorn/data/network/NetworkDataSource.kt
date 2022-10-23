package com.wooyj.alicorn.data.network

import com.wooyj.alicorn.data.model.ModelChatContent
import com.wooyj.alicorn.data.model.ModelChatList
import com.wooyj.alicorn.data.model.ModelUser

/**
 *
 * network 통신 시 필요한 data source 모음.
 *
 */
interface NetworkDataSource {

    suspend fun login(id: String, pw: String): ModelUser?

    suspend fun signIn(
        id: String,
        pw: String,
        name: String,
        company: String,
        position: String
    ): Unit?

    suspend fun logout(id: String): Unit?

    suspend fun findUser(keyword: String): List<ModelUser>?

    suspend fun getConnectedUsers(keyword: String): List<ModelUser>?

    suspend fun getChatList(id: String): List<ModelChatList>?

    /** 소켓 통신 시에는 필요없음. */
    suspend fun getChatContent(chatId: Long): List<ModelChatContent>?

}