package com.wooyj.alicorn.data.model

enum class Status {
    SUCCESS,
    ERROR,
    LOADING
}

/**
 *  data class : [Resource]
 *
 *   @param status : enum class인 Status의 상태값
 *   @param data : network 통신 결과로 받은 data 값
 *   @param message : network 통신 실패 시 생성되는 message 값
 *
 */
data class Resource<out T>(
    val status : Status,
    val data : T?,
    val message : String?
){
    companion object{
        fun <T> success(data:T?) : Resource<T>{
            return Resource(Status.SUCCESS, data, null)
        }
        fun <T> error(msg:String, data:T?) : Resource<T>{
            return Resource(Status.ERROR, data, msg)
        }
        fun <T> loading(data:T?) : Resource<T>{
            return Resource(Status.LOADING, data, null)
        }
    }
}