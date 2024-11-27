package edu.iesam.loginexam1eval.features.user.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.iesam.loginexam1eval.features.user.domain.DeleteLastLoggedUserUseCase
import edu.iesam.loginexam1eval.features.user.domain.GestLastLoggedUserUseCase
import edu.iesam.loginexam1eval.features.user.domain.LoginUseCase
import edu.iesam.loginexam1eval.features.user.domain.RememberUserUseCase
import edu.iesam.loginexam1eval.features.user.domain.RemoveUserUseCase
import edu.iesam.loginexam1eval.features.user.domain.SignUpUseCase
import edu.iesam.loginexam1eval.features.user.domain.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class LoginViewModel(
    private val signUpUseCase: SignUpUseCase,
    private val loginUseCase: LoginUseCase,
    private val rememberUseCase: RememberUserUseCase,
    private val deleteLastLoggedUserUseCase: DeleteLastLoggedUserUseCase,
    private val getLastLoggedUserUseCase: GestLastLoggedUserUseCase,
    private val removeUseCase: RemoveUserUseCase
) : ViewModel() {

    private val _uiState = MutableLiveData<UiState>()
    val uiState: LiveData<UiState> = _uiState

    fun saveUser(user: User) {
        executeUseCase(signUpUseCase::invoke, user)
    }

    fun logUser(user: User) {
        executeUseCase(loginUseCase::invoke, user)
    }
    fun removeUser(user: User) {
        executeUseCase(removeUseCase::invoke, user)
    }

    fun rememberLastLoggedUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            rememberUseCase.invoke(user)
        }
    }

    fun removeLastLoggedUser() {
        viewModelScope.launch(Dispatchers.IO) {
            deleteLastLoggedUserUseCase.invoke()
        }
    }

    private fun executeUseCase(useCase: suspend (User) -> Boolean, user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = useCase.invoke(user)
            _uiState.postValue(
                UiState(
                    isSuccess = response
                )
            )
        }
    }

    fun getLastLoggedUser() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = getLastLoggedUserUseCase.invoke()
            _uiState.postValue(
                UiState(
                    user = response
                )
            )
        }
    }


    data class UiState(
        val user: User? = null,
        val isSuccess: Boolean? = false
    )
}