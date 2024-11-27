package edu.iesam.loginexam1eval.features.user.data

import edu.iesam.loginexam1eval.features.user.data.local.LoginXmlLocalDataSource
import edu.iesam.loginexam1eval.features.user.domain.User
import edu.iesam.loginexam1eval.features.user.domain.UserRepository
import org.koin.core.annotation.Single

@Single
class UserDataRepository(
    private val xmlLocalDataSource: LoginXmlLocalDataSource
) : UserRepository {
    override suspend fun getUserById(id: String): User? {
        return xmlLocalDataSource.findById(id)
    }
    override suspend fun saveUser(user: User) {
         xmlLocalDataSource.save(user)
    }
}