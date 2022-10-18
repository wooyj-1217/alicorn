package com.wooyj.alicorn.ui.pages

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.wooyj.alicorn.R
import com.wooyj.alicorn.data.model.ModelChatContent
import com.wooyj.alicorn.databinding.FragmentChatDetailBinding
import com.wooyj.alicorn.ui.adapter.ChatDetailAdapter
import com.wooyj.alicorn.ui.adapter.ChatListAdapter
import com.wooyj.alicorn.ui.adapter.ChatListClickListener
import dagger.hilt.android.AndroidEntryPoint

/**
 *
 * 채팅 내용 상세를 보여주는 fragment
 *
 */

@AndroidEntryPoint
class ChatDetailFragment : Fragment() {

    private var _binding: FragmentChatDetailBinding? = null
    private val binding get() = _binding!!

    val args: ChatDetailFragmentArgs by navArgs()
    private lateinit var adapter: ChatDetailAdapter

    private val viewModel: ChatDetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentChatDetailBinding.inflate(inflater, container, false)
        setRecyclerViewAdapter()
        initUI()
        initEvent()
        observeViewModel()
        return binding.root
    }

    private fun setRecyclerViewAdapter() {
        adapter = ChatDetailAdapter()
        binding.rvChatContent.adapter = adapter
    }

    private fun observeViewModel() {
        viewModel.apply {
            res.observe(viewLifecycleOwner) {
                val beforeListSize = adapter.currentList.size
                adapter.submitList(it.data!!.toMutableList())
                if (beforeListSize < it.data!!.size) {
                    binding.rvChatContent.smoothScrollToPosition(it.data!!.size - 1)
                }
            }
        }
    }

    private fun initUI() {
        binding.toolbar.apply {
            title = args.name
            subtitle = String.format(
                resources.getString(R.string.format_position_company),
                args.position,
                args.company
            )
        }
    }

    private fun initEvent() {
        binding.apply {
            btnClose.setOnClickListener {
                findNavController().popBackStack()
            }
            btnSend.setOnClickListener {
                viewModel.sendMessage(
                    ModelChatContent(
                        viewModel.model.id,
                        etMessage.text.toString(),
                        System.currentTimeMillis(),
                        true
                    )
                )
                etMessage.setText("")
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}