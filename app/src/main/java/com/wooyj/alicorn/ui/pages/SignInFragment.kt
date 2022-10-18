package com.wooyj.alicorn.ui.pages

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.wooyj.alicorn.R
import com.wooyj.alicorn.data.model.ModelUser
import com.wooyj.alicorn.databinding.FragmentSignInBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
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
            result.observe(viewLifecycleOwner) {
                if (it.id == "") {
                    //TODO("가입 실패 시 처리해야 함.")
                } else {
                    findNavController().navigate(R.id.action_signInFragment_to_myPageFragment)
                }
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
                CoroutineScope(Dispatchers.IO).launch {
                    viewModel.signIn(
                        ModelUser(
                            etId.text.toString(),
                            etPw.text.toString(),
                            etName.text.toString(),
                            etCompany.text.toString(),
                            etPosition.text.toString()
                        )
                    )
                }
            }
        }
    }


}