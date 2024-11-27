package edu.iesam.loginexam1eval.features.user.domain

interface UserRepository {
    suspend fun getUserById(id : String) : User?
    suspend fun saveUser(user: User)
    suspend fun getLastLoggedUserById() : User?
    suspend fun saveLastLoggedUser(user: User)
    suspend fun deleteLastLoggedUser(id: String)
}