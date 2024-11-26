package edu.iesam.loginexam1eval.features.user.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import edu.iesam.loginexam1eval.R
import edu.iesam.loginexam1eval.databinding.FragmentSignUpBinding
import edu.iesam.loginexam1eval.features.user.domain.User
import org.koin.androidx.viewmodel.ext.android.viewModel

class SignUpFragment : Fragment() {

    private val viewModel: LoginViewModel by viewModel()

    private var _binding: FragmentSignUpBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSignUpBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()

    }

    private fun setupView() {
        val username = binding.username.text.toString()
        val password = binding.password.text.toString()
        val user = User(username, password)
        viewModel.saveUser(user)
        setupObserver()
    }

    private fun setupObserver() {
        val observer = Observer<LoginViewModel.UiState> { uiState ->
            uiState.isSuccess?.let { boolean->
                if(boolean){
                Log.d("@dev","User saved succesfully")
                }else{
                    Log.d("@dev","Said user already exists")
                }
            }
        }
        viewModel.uiState.observe(viewLifecycleOwner, observer)
    }

}