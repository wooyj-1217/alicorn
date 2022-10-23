package com.wooyj.alicorn.data.repository

import com.wooyj.alicorn.data.model.ModelChatContent
import com.wooyj.alicorn.data.model.ModelChatList
import com.wooyj.alicorn.data.model.ModelUser
import com.wooyj.alicorn.data.model.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

/**
 *
 * 채팅 관련 repository
 *
 */
interface ChatRepository {

    fun getChatListStream(id: String): Flow<Resource<List<ModelChatList>?>>
    fun getChatContent(chatId: Long): Flow<Resource<List<ModelChatContent>?>>
    fun findUser(keyword: String): Flow<Resource<List<ModelUser>?>>

}