package edu.iesam.loginexam1eval.features.user.di

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import edu.iesam.loginexam1eval.R
import edu.iesam.loginexam1eval.features.user.data.local.LoginXmlLocalDataSource
import edu.iesam.loginexam1eval.features.user.data.local.UserPreferencesXmlLocalDataSource
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.core.annotation.Named
import org.koin.core.annotation.Single

@Module
@ComponentScan
class UserModule {
    @Single
    @Named("xml_users")
    fun provideSharedPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(
            context.getString(R.string.xml_users), Context.MODE_PRIVATE
        )
    }

    @Single
    @Named("xml_user_preferences")
    fun provideSharedPreferencesToUserPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(
            context.getString(R.string.xml_user_preferences), Context.MODE_PRIVATE
        )
    }

    @Single
    fun provideLoginXmlLocalDataSource(
        @Named("xml_users")sharedPref: SharedPreferences,
        gson: Gson
    ): LoginXmlLocalDataSource {
        return LoginXmlLocalDataSource(sharedPref,gson)
    }

    @Single
    fun provideUserPreferencesLocalDataSource(
        @Named("xml_user_preferences") sharedPrefToUserPrefs: SharedPreferences,
        gson: Gson
    ): UserPreferencesXmlLocalDataSource {
        return UserPreferencesXmlLocalDataSource(sharedPrefToUserPrefs,gson)
    }

}