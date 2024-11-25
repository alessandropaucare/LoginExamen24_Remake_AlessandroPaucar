package edu.iesam.loginexam1eval.features.user.data.local

import android.content.Context
import com.google.gson.Gson
import edu.iesam.loginexam1eval.features.user.domain.User
import java.util.ArrayList
import kotlin.collections.forEach
import kotlin.jvm.java
import kotlin.let

class LoginXmlLocalDataSource (private val context: Context) {

    private val sharedPref = context.getSharedPreferences(
        "user-storage", Context.MODE_PRIVATE
    )

    private val gson = Gson()

    fun save(user: User) {
        val editor = sharedPref.edit()
        editor.putString(user.id, gson.toJson(user))
        editor.apply()
    }

    fun saveAll(users: List<User>) {
        val editor = sharedPref.edit()
        users.forEach { user ->
            editor.putString(user.id, gson.toJson(user))
        }
        editor.apply()
    }

    fun findAll(): List<User>{
        val users = ArrayList<User>()
        val mapUsers = sharedPref.all
        mapUsers.values.forEach { jsonUser ->
            val movie = gson.fromJson(jsonUser as String, User::class.java)
            users.add(movie)
        }
        return users
    }

    fun findById(userId: String): User?{
        return sharedPref.getString(userId, null)?.let { movie ->
            gson.fromJson(movie, User::class.java)
        }
    }

    fun delete() {
        sharedPref.edit().clear().apply()
    }

    fun deleteById(movieId: String){
        sharedPref.edit().remove(movieId).commit()
    }
}