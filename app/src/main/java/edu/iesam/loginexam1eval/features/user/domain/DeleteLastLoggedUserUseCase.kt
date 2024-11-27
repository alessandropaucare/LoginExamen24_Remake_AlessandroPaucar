package edu.iesam.loginexam1eval.features.user.domain

import org.koin.core.annotation.Single

@Single
class DeleteLastLoggedUserUseCase(
    private val repository: UserRepository
) {
    suspend operator fun invoke(){
        repository.deleteLastLoggedUser()
    }
}