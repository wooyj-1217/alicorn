package com.wooyj.alicorn.data.model

data class ModelChatList(
    val id : Long,
    val name : String,
    val position:String,
    val company:String,
    val imgUrl : String,
    val lastMessage : String,
    val time: String,
    val notReadCount : Int
)