package edu.iesam.loginexam1eval.features.user.di

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import edu.iesam.loginexam1eval.R
import edu.iesam.loginexam1eval.features.user.data.local.LoginXmlLocalDataSource
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single

@Module
@ComponentScan
class UserModule {
    @Single
    fun provideSharedPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(
            context.getString(R.string.xml_users), Context.MODE_PRIVATE
        )
    }

    @Single
    fun provideLoginXmlLocalDataSource(
        sharedPref: SharedPreferences,
        gson: Gson
    ): LoginXmlLocalDataSource {
        return LoginXmlLocalDataSource(sharedPref,gson)
    }

}