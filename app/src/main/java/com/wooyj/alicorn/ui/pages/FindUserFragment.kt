package com.wooyj.alicorn.ui.pages

import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.TextView.OnEditorActionListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.wooyj.alicorn.databinding.FragmentFindUserBinding
import com.wooyj.alicorn.ui.adapter.FindUserAdapter
import com.wooyj.alicorn.ui.adapter.UserListClickListener
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class FindUserFragment : Fragment() {

    private var _binding: FragmentFindUserBinding? = null
    private val binding get() = _binding!!

    private val viewModel: FindUserViewModel by viewModels()

    private lateinit var adapter:FindUserAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFindUserBinding.inflate(inflater, container, false)
        setRecyclerViewAdapter()
        observeViewModel()
        initEvent()
        return binding.root
    }

    private fun setRecyclerViewAdapter(){
        adapter = FindUserAdapter(UserListClickListener {
            //TODO("선택 시 채팅방이 만들어져야함.")
        })
        binding.rvUserList.adapter = adapter
    }

    private fun observeViewModel(){
        viewModel.apply {
            result.observe(viewLifecycleOwner){
                if(it != null) {
                    adapter.submitList(it.toMutableList())
                }
            }
        }
    }


    private fun initEvent() {
        binding.apply {
            btnClose.setOnClickListener {
                findNavController().popBackStack()
            }
            etReceiver.setOnEditorActionListener { _, actionId, event ->
                var handled = false
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    viewModel.findUser(etReceiver.text.toString())
                    handled = true
                }
                handled
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}