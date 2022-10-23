package com.wooyj.alicorn.ui.pages

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.wooyj.alicorn.R
import com.wooyj.alicorn.data.model.ModelUser
import com.wooyj.alicorn.data.model.Status
import com.wooyj.alicorn.databinding.FragmentSignInBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SignInFragment : Fragment() {

    private var _binding: FragmentSignInBinding? = null
    private val binding get() = _binding!!

    private val viewModel: SignInViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSignInBinding.inflate(inflater, container, false)
        initEvent()
        observeViewModel()
        return binding.root
    }

    private fun observeViewModel() {
        viewModel.apply {
            valid.observe(viewLifecycleOwner) {
                binding.btnSignIn.isEnabled = it
            }
        }
    }


    private fun initEvent() {
        binding.apply {
            etId.setOnFocusChangeListener { view, hasFocus ->
                if (!hasFocus) {
                    if (etId.text.toString().trim().isNotEmpty()) {
                        viewModel.validationIdCheck(etId.text.toString())
                    } else {
                        btnSignIn.isEnabled = false
                    }
                }
            }

            btnClose.setOnClickListener {
                findNavController().popBackStack()
            }
            btnSignIn.setOnClickListener {
                lifecycleScope.launch {
                    viewModel.signIn(
                        ModelUser(
                            etId.text.toString(),
                            etPw.text.toString(),
                            etName.text.toString(),
                            etCompany.text.toString(),
                            etPosition.text.toString(),
                            ""      //TODO("이미지 res data 전송 필요.")
                        )
                    ).collect {
                        when(it.status){
                            Status.SUCCESS->{
                                findNavController().navigate(R.id.action_signInFragment_to_loginFragment)
                            }
                            Status.LOADING->{}
                            Status.ERROR->{}
                        }
                    }
                }
            }
        }
    }

}