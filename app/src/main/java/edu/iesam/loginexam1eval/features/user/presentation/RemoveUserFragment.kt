package edu.iesam.loginexam1eval.features.user.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import edu.iesam.loginexam1eval.databinding.FragmentRemoveUserBinding
import edu.iesam.loginexam1eval.features.user.domain.User
import org.koin.androidx.viewmodel.ext.android.viewModel

class RemoveUserFragment : Fragment() {

    private val viewModel: LoginViewModel by viewModel()
    private val toastMessage = "Warning.This action may be permanent. Please reed our policy"
    private var _binding: FragmentRemoveUserBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRemoveUserBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Toast.makeText(context, toastMessage ,Toast.LENGTH_SHORT).show()
        setupObserver()
        setupView()
    }
    private fun setupView() {
        binding.buttonRemove.setOnClickListener{
            binding.apply {
                val username = username.text.toString()
                val password = password.text.toString()
                viewModel.removeUser(User(username,password))
                viewModel.removeLastLoggedUser()
            }

        }
    }
    private fun setupObserver() {
        val observer = Observer<LoginViewModel.UiState> { uiState ->
            uiState.isSuccess?.let { isSuccess->
                if(isSuccess){
                    Log.d("@dev","User removed successfully")
                    navigate()
                }else{
                    Log.d("@dev","Said user does not exists or wrong password")
                }
            }
        }
        viewModel.uiState.observe(viewLifecycleOwner, observer)
    }

    private fun navigate(){
        findNavController().navigate(RemoveUserFragmentDirections.removeToSignUp())
    }



}