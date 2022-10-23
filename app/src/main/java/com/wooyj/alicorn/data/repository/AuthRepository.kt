package com.wooyj.alicorn.data.repository

import com.wooyj.alicorn.data.model.ModelUser
import com.wooyj.alicorn.data.model.Resource
import kotlinx.coroutines.flow.Flow

/**
 *
 * 로그인, 로그아웃, 회원가입 관련 통신
 *
 */
interface AuthRepository {

    fun loginStream(id: String, pw: String): Flow<Resource<ModelUser>>
    fun logoutStream(id: String): Flow<Resource<Unit>>
    fun signInStream(
        id: String,
        pw: String,
        name: String,
        company: String,
        position: String
    ): Flow<Resource<Unit>>

}