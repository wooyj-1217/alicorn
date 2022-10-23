package com.wooyj.alicorn.ui.pages

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.wooyj.alicorn.data.model.ModelChatList
import com.wooyj.alicorn.data.model.ModelUser
import com.wooyj.alicorn.data.model.Resource
import com.wooyj.alicorn.data.model.Status
import com.wooyj.alicorn.data.repository.ChatRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FindUserViewModel @Inject constructor(
    private val chatRepository: ChatRepository
) : ViewModel() {

    fun getConnectedUserList() = chatRepository.findUser("")

    fun findUser(keyword: String) = chatRepository.findUser(keyword)

}