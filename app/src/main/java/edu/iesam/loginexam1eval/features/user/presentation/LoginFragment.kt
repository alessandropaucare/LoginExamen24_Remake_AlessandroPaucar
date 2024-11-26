package edu.iesam.loginexam1eval.features.user.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import edu.iesam.loginexam1eval.databinding.FragmentLoginBinding
import edu.iesam.loginexam1eval.features.user.domain.User
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : Fragment() {

    private val viewModel: LoginViewModel by viewModel()

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObserver()
        setupView()


    }
    private fun setupView() {
        binding.buttonLogin.setOnClickListener {
            binding.apply {
                val username = username.text.toString()
                val password = password.text.toString()
                val user = User(username, password)
                viewModel.logUser(user)
            }
        }
    }

    private fun setupObserver() {
        val observer = Observer<LoginViewModel.UiState> { uiState ->
            uiState.isSuccess?.let { isSuccess->
                if(isSuccess){
                    Log.d("@dev","Login in...")
                    navigate()
                }else{
                    Log.d("@dev","Sorry wrong password or username. Try again.")
                }
            }
        }
        viewModel.uiState.observe(viewLifecycleOwner, observer)
    }
    private fun navigate(){
        findNavController().navigate(LoginFragmentDirections.loginToWelcome())
    }
    private fun isChecked(){
        val reminder = binding.reminder.isChecked
        if(reminder){

        }

    }

}