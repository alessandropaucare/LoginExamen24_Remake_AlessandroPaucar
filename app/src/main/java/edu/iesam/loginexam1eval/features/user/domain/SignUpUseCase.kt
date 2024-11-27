package edu.iesam.loginexam1eval.features.user.domain

import org.koin.core.annotation.Single

@Single
class SignUpUseCase(
    private val repository: UserRepository
) {
    suspend operator fun invoke(user: User): Boolean {
        val userCheck : User? = repository.getUserById(user.id)
        userCheck?.let { return false }
        repository.saveUser(user)
        return true

    }
}