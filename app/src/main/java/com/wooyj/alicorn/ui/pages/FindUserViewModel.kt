package com.wooyj.alicorn.ui.pages

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.wooyj.alicorn.data.model.ModelChatList
import com.wooyj.alicorn.data.model.ModelUser
import com.wooyj.alicorn.data.model.Resource
import com.wooyj.alicorn.data.model.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@HiltViewModel
class FindUserViewModel @Inject constructor() : ViewModel() {

    private var _result = MutableLiveData<MutableList<ModelUser>>()
    val result: LiveData<MutableList<ModelUser>>
        get() = _result

    private var list = mutableListOf(
        ModelUser(
            id = "test1",
            pw = "",
            name = "a",
            company = "company",
            position = "position",
            imgUrl = ""
        ), ModelUser(
            id = "test2",
            pw = "",
            name = "b",
            company = "company",
            position = "position",
            imgUrl = ""
        ), ModelUser(
            id = "test3",
            pw = "",
            name = "c",
            company = "company",
            position = "position",
            imgUrl = ""
        ), ModelUser(
            id = "test4",
            pw = "",
            name = "d",
            company = "company",
            position = "position",
            imgUrl = ""
        )
    )

//    fun execute(): Flow<Resource<List<ModelUser>>> = flow {
//        emit(Resource.loading(null))
//        emit(Resource.success(list.toList()))
//    }

    init{
        getConnectedUserList()
    }

    fun getConnectedUserList(){
        _result.postValue(list)
    }


    fun findUser(userName: String) {
        //TODO("서버 통신 필요.")
        var copiedList = list.toMutableList()
        _result.postValue(copiedList.filter { it.name.contains(userName) }.toMutableList())
    }



}