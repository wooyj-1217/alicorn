package com.wooyj.alicorn.ui.pages

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.wooyj.alicorn.data.model.Status
import com.wooyj.alicorn.databinding.FragmentFindUserBinding
import com.wooyj.alicorn.ui.adapter.FindUserAdapter
import com.wooyj.alicorn.ui.adapter.UserListClickListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class FindUserFragment : Fragment() {

    private var _binding: FragmentFindUserBinding? = null
    private val binding get() = _binding!!

    private val viewModel: FindUserViewModel by viewModels()

    private lateinit var adapter: FindUserAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFindUserBinding.inflate(inflater, container, false)
        setRecyclerViewAdapter()
        getConnectedUserList()
        initEvent()
        return binding.root
    }

    private fun setRecyclerViewAdapter() {
        adapter = FindUserAdapter(UserListClickListener {
            //TODO("선택 시 채팅방이 만들어져야함.")
        })
        binding.rvUserList.adapter = adapter
    }


    private fun initEvent() {
        binding.apply {
            btnClose.setOnClickListener {
                findNavController().popBackStack()
            }
            etReceiver.setOnEditorActionListener { _, actionId, event ->
                var handled = false
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    if (etReceiver.text.toString().trim() != "") {
                        findUser(etReceiver.text.toString())
                    } else {
                        getConnectedUserList()
                    }
                    handled = true
                }
                handled
            }
        }
    }

    private fun findUser(keyword: String) = lifecycleScope.launch {
        viewModel.findUser(keyword).collect {
            when (it.status) {
                Status.SUCCESS -> {
                    adapter.submitList(it.data?.toMutableList())
                }
                Status.LOADING -> {}
                Status.ERROR -> {}
            }
        }
    }


    private fun getConnectedUserList() = lifecycleScope.launch {
        viewModel.getConnectedUserList().collect {
            when (it.status) {
                Status.SUCCESS -> {
                    adapter.submitList(it.data?.toMutableList())
                }
                Status.LOADING -> {}
                Status.ERROR -> {}
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}