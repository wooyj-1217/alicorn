package com.wooyj.alicorn.data.network.retrofit

import com.wooyj.alicorn.BuildConfig
import com.wooyj.alicorn.data.model.ModelChatContent
import com.wooyj.alicorn.data.model.ModelChatList
import com.wooyj.alicorn.data.model.ModelUser
import com.wooyj.alicorn.data.model.Resource
import com.wooyj.alicorn.data.network.NetworkDataSource
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import javax.inject.Singleton


private const val BaseUrl = BuildConfig.BASE_URL

private interface RetrofitApi {

    @POST("/login")
    suspend fun login(id: String, pw: String): ModelUser?

    @GET("/findUser/filter")
    suspend fun findUser(@Query("keyword") keyword: String): List<ModelUser>?

    @GET("/getConnectedUsers")
    suspend fun getConnectedUsers(id : String): List<ModelUser>?

    @POST("/chatList")
    suspend fun getChatList(id: String): List<ModelChatList>?

    /** 소켓 통신 시에는 필요없음. */
    @POST("/chatContent")
    suspend fun getChatContent(chatId: Long): List<ModelChatContent>?

    @POST("/logout")
    suspend fun logout(id: String): Unit

    @POST("/signIn")
    suspend fun signIn(
        id: String,
        pw: String,
        name: String,
        company: String,
        position: String
    ): Unit

}


@Singleton
class RetrofitNetworkDataSource : NetworkDataSource {

    private val api = Retrofit.Builder()
        .baseUrl(BaseUrl)
        .client(
            OkHttpClient.Builder().addInterceptor(HttpLoggingInterceptor().apply {
                setLevel(HttpLoggingInterceptor.Level.BODY)
            }).build()
        ).build().create(RetrofitApi::class.java)


    override suspend fun login(id: String, pw: String): ModelUser? = api.login(id, pw)

    override suspend fun signIn(
        id: String,
        pw: String,
        name: String,
        company: String,
        position: String
    ): Unit? = api.signIn(id, pw, name, company, position)

    override suspend fun logout(id: String): Unit? = api.logout(id)

    override suspend fun findUser(keyword: String): List<ModelUser>? = api.findUser(keyword)
    override suspend fun getConnectedUsers(id: String): List<ModelUser>? = api.getConnectedUsers(id)
    override suspend fun getChatList(id: String): List<ModelChatList>? = api.getChatList(id)

    override suspend fun getChatContent(chatId: Long): List<ModelChatContent>? =
        api.getChatContent(chatId)


}