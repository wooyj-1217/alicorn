package com.wooyj.alicorn.data.model

data class ModelChatList(
    val id: Long,
    val user: ModelUser,
    val lastMessage: String,
    val time: String,
    val notReadCount: Int
)