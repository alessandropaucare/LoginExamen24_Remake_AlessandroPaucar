package edu.iesam.loginexam1eval.core

import android.app.Application
import android.util.Log
import edu.iesam.loginexam1eval.core.di.AppModule
import edu.iesam.loginexam1eval.features.user.di.UserModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.ksp.generated.module

class Loginexam1eval : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidContext(this@Loginexam1eval)
            modules(
                AppModule().module,
                UserModule().module
            )
        }

    }
}