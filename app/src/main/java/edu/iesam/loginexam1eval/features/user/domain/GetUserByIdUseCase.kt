package edu.iesam.loginexam1eval.features.user.domain

class GetUserByIdUseCase(
    val repository: UserRepository
) {
    /*
    suspend operator fun invoke(id : String): User = repository.getUserById(id)
     */
}