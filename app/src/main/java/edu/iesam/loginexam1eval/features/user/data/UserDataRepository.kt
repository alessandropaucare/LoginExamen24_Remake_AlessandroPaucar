package edu.iesam.loginexam1eval.features.user.data

import edu.iesam.loginexam1eval.features.user.data.local.LoginXmlLocalDataSource
import edu.iesam.loginexam1eval.features.user.domain.User
import edu.iesam.loginexam1eval.features.user.domain.UserRepository
import java.io.IOException

class UserDataRepository(
    private val xmlLocalDataSource: LoginXmlLocalDataSource
) : UserRepository {
    override suspend fun getUserById(id: String): User {
        TODO("Not yet implemented")
    }

    override suspend fun saveUser(user: User): Result<User> {
        val requestedUser = getUserById(user.id)
        return if(user.id==requestedUser.id){
            Result.failure(error("User already registered"))
        }else{
            Result.success(user)
        }
    }
}