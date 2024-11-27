package edu.iesam.loginexam1eval.features.user.domain

import org.koin.core.annotation.Single

@Single
class RememberUserUseCase(
    private val repository: UserRepository
) {
    suspend operator fun invoke(user: User){
        repository.saveLastLoggedUser(user)
    }
}