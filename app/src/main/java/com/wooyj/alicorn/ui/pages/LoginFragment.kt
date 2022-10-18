package com.wooyj.alicorn.ui.pages

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.wooyj.alicorn.R
import com.wooyj.alicorn.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private val viewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        observeViewModel()
        initEvent()
        return binding.root
    }

    private fun observeViewModel(){
        viewModel.apply {
            result.observe(viewLifecycleOwner){
                if(it.id != ""){
                    findNavController().popBackStack()
                }
            }
        }
    }

    private fun initEvent(){
        binding.apply {
            btnClose.setOnClickListener {
                findNavController().popBackStack()
            }
            btnLogin.setOnClickListener {
                viewModel.login(etId.text.toString(), etPw.text.toString())
            }
            tvSignIn.setOnClickListener {
                findNavController().navigate(R.id.action_loginFragment_to_signInFragment)
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}