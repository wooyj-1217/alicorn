package com.wooyj.alicorn.ui.pages

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.wooyj.alicorn.R
import com.wooyj.alicorn.data.model.Status
import com.wooyj.alicorn.databinding.FragmentChatListBinding
import com.wooyj.alicorn.ui.adapter.ChatListAdapter
import com.wooyj.alicorn.ui.adapter.ChatListClickListener
import dagger.hilt.android.AndroidEntryPoint

/**
 *
 * 채팅 리스트를 보여주는 fragment
 *
 * @author wooyj
 *
 */

@AndroidEntryPoint
class ChatListFragment : Fragment() {

    private var _binding: FragmentChatListBinding? = null
    private val binding get() = _binding!!

    private val viewModel : ChatListViewModel by viewModels()
    private lateinit var adapter: ChatListAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentChatListBinding.inflate(inflater, container, false)
        setRecyclerViewAdapter()
        observeViewModel()
        initEvent()
        return binding.root
    }

    private fun setRecyclerViewAdapter(){
        adapter = ChatListAdapter(ChatListClickListener { data->
            val action = ChatListFragmentDirections.actionChatListFragmentToChatDetailFragment(data.id, data.user.name, data.user.position, data.user.company)
            findNavController().navigate(action)
        })
        binding.rvChatList.adapter = adapter
    }

    private fun observeViewModel(){
        viewModel.apply {
            res.observe(viewLifecycleOwner){
                if(it.status == Status.SUCCESS) {
                    adapter.submitList(it.data)
                }
            }
        }
    }

    private fun initEvent(){
        binding.apply {
            tvNewMessage.setOnClickListener {
                findNavController().navigate(R.id.action_ChatListFragment_to_findUserFragment)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}