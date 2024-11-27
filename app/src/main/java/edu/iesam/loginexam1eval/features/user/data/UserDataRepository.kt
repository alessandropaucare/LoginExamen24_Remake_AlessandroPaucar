package edu.iesam.loginexam1eval.features.user.data

import edu.iesam.loginexam1eval.features.user.data.local.LoginXmlLocalDataSource
import edu.iesam.loginexam1eval.features.user.data.local.UserPreferencesXmlLocalDataSource
import edu.iesam.loginexam1eval.features.user.domain.User
import edu.iesam.loginexam1eval.features.user.domain.UserRepository
import org.koin.core.annotation.Single

@Single
class UserDataRepository(
    private val xmlLocalDataSource: LoginXmlLocalDataSource,
    private val xmlUserPreferencesXmlLocalDataSource: UserPreferencesXmlLocalDataSource
) : UserRepository {
    override suspend fun getUserById(id: String): User? {
        return xmlLocalDataSource.findById(id)
    }
    override suspend fun saveUser(user: User) {
         xmlLocalDataSource.save(user)
    }

    override suspend fun getLastLoggedUserById(): User? {
        return xmlUserPreferencesXmlLocalDataSource.findStoredUser()
    }

    override suspend fun saveLastLoggedUser(user: User){
        xmlUserPreferencesXmlLocalDataSource.save(user)
    }

    override suspend fun deleteLastLoggedUser(id: String) {
        xmlUserPreferencesXmlLocalDataSource.deleteById(id)
    }

}