package com.wooyj.alicorn.ui.pages

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.wooyj.alicorn.R
import com.wooyj.alicorn.databinding.FragmentMypageBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MyPageFragment : Fragment() {

    private var _binding: FragmentMypageBinding? = null
    private val binding get() = _binding!!

    private val viewModel : MyPageViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMypageBinding.inflate(inflater, container, false)
        observeViewModel()
        initEvent()
        return binding.root
    }

    private fun observeViewModel(){
        viewModel.apply {
            loggedIn.observe(viewLifecycleOwner){
                showUIByLoggedIn(it.id != "")
                binding.apply {
                    tvName.text = it.name
                    tvPosition.text = String.format(
                        resources.getString(R.string.format_position_company),
                        it.position,
                        it.company
                    )
                }
            }
        }
    }


    private fun initEvent(){
        binding.apply {
            btnLogin.setOnClickListener {
                findNavController().navigate(R.id.action_myPageFragment_to_loginFragment)
            }
        }
    }

    private fun showUIByLoggedIn(loggedIn: Boolean) {
        binding.apply {
            tvNeedLogin.isVisible = !loggedIn
            btnLogin.isVisible = !loggedIn
            ivProfile.isVisible = loggedIn
            tvName.isVisible = loggedIn
            divider.isVisible = loggedIn
            tvPosition.isVisible = loggedIn
        }
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}