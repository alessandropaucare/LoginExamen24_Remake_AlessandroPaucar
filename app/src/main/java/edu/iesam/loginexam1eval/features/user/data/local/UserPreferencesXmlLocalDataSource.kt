package edu.iesam.loginexam1eval.features.user.data.local

import android.content.SharedPreferences
import com.google.gson.Gson
import edu.iesam.loginexam1eval.features.user.domain.User
import org.koin.core.annotation.Single

@Single
class UserPreferencesXmlLocalDataSource (
    private val sharedPrefUserPref: SharedPreferences,
    private val gson: Gson = Gson()
) {
    fun save(user: User) {
        val editor = sharedPrefUserPref.edit()
        editor.putString("user", gson.toJson(user))
        editor.apply()
    }

    fun findStoredUser(): User?{
        val storedUser = sharedPrefUserPref.getString("user", null)
        return gson.fromJson(storedUser,User::class.java)
    }

    fun deleteById(userId: String){
        sharedPrefUserPref.edit().remove(userId).commit()
    }
}