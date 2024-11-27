package edu.iesam.loginexam1eval.features.user.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.iesam.loginexam1eval.features.user.domain.LoginUseCase
import edu.iesam.loginexam1eval.features.user.domain.SignUpUseCase
import edu.iesam.loginexam1eval.features.user.domain.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class LoginViewModel(
    private val signUpUseCase: SignUpUseCase,
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    private val _uiState = MutableLiveData<UiState>()
    val uiState: LiveData<UiState> = _uiState

    fun saveUser(user : User) {
        executeUseCase(signUpUseCase::invoke,user)
    }
    fun logUser(user : User) {
        executeUseCase(loginUseCase::invoke,user)
    }

    private fun executeUseCase(useCase: suspend (User)->Boolean,user: User){
        viewModelScope.launch(Dispatchers.IO){
            val response = useCase.invoke(user)
            _uiState.postValue(
                UiState(
                    isSuccess = response
                )
            )
        }
    }

    data class UiState(
        val isSuccess: Boolean? = false
    )
}