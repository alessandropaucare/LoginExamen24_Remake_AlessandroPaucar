package edu.iesam.loginexam1eval.features.user.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import edu.iesam.loginexam1eval.R
import edu.iesam.loginexam1eval.databinding.FragmentSignUpBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class SignUpFragment : Fragment() {

    private val viewModel: UserViewModel by viewModel()

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

    }

    private fun setupObserver(){
        val observer = Observer<UserViewModel.UiState>{uiState->
            uiState.user?.let {
                findNavController().navigate(R.id.welcome)
            }
            uiState.error?.let {
                Log.d("@dev", "Said User is alredy registered")
            }

        }
    }
    private fun setupView() {
        val username = findViewById<EditText>(R.id.username).text.toString()
        val password = findViewById<EditText>(R.id.password).text.toString()
    }

}