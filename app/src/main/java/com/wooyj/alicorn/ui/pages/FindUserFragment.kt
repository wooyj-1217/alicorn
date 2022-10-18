package com.wooyj.alicorn.ui.pages

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.wooyj.alicorn.databinding.FragmentFindUserBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FindUserFragment : Fragment() {

    private var _binding: FragmentFindUserBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFindUserBinding.inflate(inflater, container, false)

        initEvent()
        return binding.root
    }


    private fun initEvent() {
        binding.apply {
            btnClose.setOnClickListener {
                findNavController().popBackStack()
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}