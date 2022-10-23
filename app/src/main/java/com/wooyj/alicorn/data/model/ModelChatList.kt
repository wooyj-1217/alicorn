package com.wooyj.alicorn.data.model

data class ModelChatList(
    val chatId: Long,
    val user: ModelUser,
    val lastMessage: String,
    val time: Long,
    val notReadCount: Int
)