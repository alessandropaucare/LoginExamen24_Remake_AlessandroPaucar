package edu.iesam.loginexam1eval.features.user.domain

class SaveUserUseCase(
    val repository: UserRepository
) {
    suspend operator fun invoke(user: User) : Result<User> {
        val response = repository.saveUser(user)
        return response
    }
}