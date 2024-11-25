package edu.iesam.loginexam1eval.features.user.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.iesam.loginexam1eval.features.user.domain.SaveUserUseCase
import edu.iesam.loginexam1eval.features.user.domain.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel

@KoinViewModel
class UserViewModel(
    val saveUseCase: SaveUserUseCase
) : ViewModel() {
    private val _uiState = MutableLiveData<UiState>()
    val uiState: LiveData<UiState> = _uiState

    fun saveUser(user : User){
        viewModelScope.launch(Dispatchers.IO){
            val result = saveUseCase.invoke(user)
            _uiState.postValue(
                UiState(
                    user = result.getOrNull(),
                    error= result.getOrNull() as Error?
                )
            )
        }
    }

    data class UiState(
        var user: User? = null,
        var error: Error? = null
    )
}