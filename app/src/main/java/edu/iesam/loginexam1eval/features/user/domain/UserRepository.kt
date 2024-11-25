package edu.iesam.loginexam1eval.features.user.domain

interface UserRepository {
    suspend fun getUserById(id : String) : User
    suspend fun saveUser(user: User): Result<User>
}