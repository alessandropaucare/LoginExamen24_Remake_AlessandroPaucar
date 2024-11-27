package edu.iesam.loginexam1eval.features.user.domain

import org.koin.core.annotation.Single

@Single
class LoginUseCase(
    private val repository: UserRepository
) {
    suspend operator fun invoke(user: User): Boolean {
        val userCheck: User? = repository.getUserById(user.id)
        return userCheck != null && user.password == userCheck.password
    }
}