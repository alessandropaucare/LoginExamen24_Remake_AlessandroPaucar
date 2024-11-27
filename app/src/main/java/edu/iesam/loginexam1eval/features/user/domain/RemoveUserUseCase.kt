package edu.iesam.loginexam1eval.features.user.domain

import org.koin.core.annotation.Single

@Single
class RemoveUserUseCase(
    private val repository: UserRepository
) {
    suspend operator fun invoke(user: User) :  Boolean {
        val userCheck = repository.getUserById(user.id)
        if(userCheck != null && user.password == userCheck.password) {
            repository.removeUserById(userCheck)
            return true
        }
        return false
    }
}