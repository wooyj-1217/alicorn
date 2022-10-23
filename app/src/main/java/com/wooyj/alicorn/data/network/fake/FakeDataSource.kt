package com.wooyj.alicorn.data.network.fake

import com.wooyj.alicorn.data.model.ModelChatContent
import com.wooyj.alicorn.data.model.ModelChatList
import com.wooyj.alicorn.data.model.ModelUser
import java.util.Calendar

/**
 *
 * 실제 데이터 통신 전까지 UI 확인 용도로 작성된 fake data
 *
 */
object FakeDataSource {

    fun sampleLogin(id: String, pw: String): ModelUser? = if (id == "test" && pw == "1234") {
        ModelUser("test", "", "테스트", "company", "position", "")
    } else {
        null
    }

    fun findUser(keyword:String): MutableList<ModelUser> {
        val list = listOf(
            ModelUser(
                id = "test1",
                name = "a",
                company = "company",
                position = "position",
                imgUrl = ""
            ), ModelUser(
                id = "test2",
                name = "b",
                company = "company",
                position = "position",
                imgUrl = ""
            ), ModelUser(
                id = "test3",
                name = "c",
                company = "company",
                position = "position",
                imgUrl = ""
            ), ModelUser(
                id = "test4",
                name = "d",
                company = "company",
                position = "position",
                imgUrl = ""
            )
        )

        return list.filter { it.name.contains(keyword) }.toMutableList()
    }

    val chatList = listOf(
        ModelChatList(
            chatId = 1L,
            ModelUser(
                name = "테스트",
                id = "test",
                pw = "",
                position = "position",
                company = "company",
                imgUrl = ""
            ),
            lastMessage = "마지막",
            time = Calendar.getInstance().apply { add(Calendar.DATE, -1) }.timeInMillis,
            notReadCount = 84
        ),
        ModelChatList(
            chatId = 2L,
            ModelUser(
                name = "테스트2",
                id = "test2",
                pw = "",
                position = "position",
                company = "company",
                imgUrl = ""
            ),
            lastMessage = "테스트용마지막메시지입니다2",
            time = Calendar.getInstance().apply { add(Calendar.DATE, -3) }.timeInMillis,
            notReadCount = 84
        ),
        ModelChatList(
            chatId = 3L,
            ModelUser(
                name = "테스트3",
                id = "test3",
                pw = "",
                position = "position",
                company = "company",
                imgUrl = ""
            ),
            lastMessage = "테스트용마지막메시지입니다3",
            time = Calendar.getInstance().apply { add(Calendar.DATE, -5) }.timeInMillis,
            notReadCount = 84
        )
    )

    val chatContent = mutableListOf(
        ModelChatContent(
            userId = "test",
            content = "1번 테스트",
            time = System.currentTimeMillis(),
            isMy = true
        ),
        ModelChatContent(
            userId = "d",
            content = "2번 테스트",
            time = System.currentTimeMillis(),
            isMy = false
        ),
        ModelChatContent(
            userId = "d",
            content = "3번 테스트",
            time = System.currentTimeMillis(),
            isMy = true
        ),
        ModelChatContent(
            userId = "test",
            content = "4번 테스트",
            time = System.currentTimeMillis(),
            isMy = false
        )
    )


}