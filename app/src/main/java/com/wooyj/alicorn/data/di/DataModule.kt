package com.wooyj.alicorn.data.di

import com.wooyj.alicorn.data.repository.AuthRepository
import com.wooyj.alicorn.data.repository.ChatRepository
import com.wooyj.alicorn.data.repository.fake.FakeAuthRepository
import com.wooyj.alicorn.data.repository.fake.FakeChatRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    //TODO("Repository가 현재는 Fake 이지만, 서버가 구현되었을 시에는 Repository를 새로 정의할 것.")

    @Binds
    fun bindsAuthRepository(fakeAuthRepository: FakeAuthRepository) : AuthRepository

    @Binds
    fun bindsChatRepository(fakeChatRepository: FakeChatRepository) : ChatRepository

}