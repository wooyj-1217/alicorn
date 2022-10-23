package com.wooyj.alicorn.ui.pages

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.gson.Gson
import com.wooyj.alicorn.R
import com.wooyj.alicorn.data.model.Status
import com.wooyj.alicorn.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

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
        initEvent()
        return binding.root
    }

    private fun initEvent() {
        binding.apply {
            btnClose.setOnClickListener {
                findNavController().popBackStack()
            }
            btnLogin.setOnClickListener {
                lifecycleScope.launch {
                    viewModel.login(etId.text.toString(), etPw.text.toString()).collect {
                        when (it.status) {
                            Status.LOADING -> {}
                            Status.SUCCESS -> {
                                if (it.data != null) {
                                    //로그인 성공 시
                                    val job = viewModel.saveUserData(Gson().toJson(it.data!!))
                                    job.join()
                                    if(job.isCompleted) {
                                        findNavController().navigate(R.id.action_loginFragment_to_myPageFragment)
                                    }
                                } else {
                                    //fake data 로그인 실패 시 상황
                                }
                            }
                            Status.ERROR -> {
                                //실제 로그인 실패 시 (네트워크 에러, 에러코드로 넘어올 경우 처리해야됨.)
                            }
                        }
                    }
                }
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