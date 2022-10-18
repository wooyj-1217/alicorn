package com.wooyj.alicorn.data.model

data class ModelChatContent(
    val userId : String,
    val content : String,
    val time : Long,
    val isMy : Boolean
)