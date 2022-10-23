package com.wooyj.alicorn.data.repository.fake

import com.wooyj.alicorn.data.model.ModelUser
import com.wooyj.alicorn.data.model.Resource
import com.wooyj.alicorn.data.network.di.IoDispatcher
import com.wooyj.alicorn.data.network.fake.FakeNetworkDataSource
import com.wooyj.alicorn.data.repository.AuthRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class FakeAuthRepository @Inject constructor(
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : AuthRepository {

    override fun loginStream(id: String, pw: String): Flow<Resource<ModelUser>> = flow {
        emit(Resource.loading(null))
        emit(Resource.success(FakeNetworkDataSource(ioDispatcher).login(id, pw)))
    }

    override fun logoutStream(id: String): Flow<Resource<Unit>> = flow {
        emit(Resource.loading(null))
        emit(Resource.success(FakeNetworkDataSource(ioDispatcher).logout(id)))
    }

    override fun signInStream(
        id: String,
        pw: String,
        name: String,
        company: String,
        position: String
    ): Flow<Resource<Unit>> = flow {
        emit(Resource.loading(null))
        emit(
            Resource.success(
                FakeNetworkDataSource(ioDispatcher).signIn(
                    id,
                    pw,
                    name,
                    company,
                    position
                )
            )
        )
    }

}