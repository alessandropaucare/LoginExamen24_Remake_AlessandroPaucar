plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.navigation.safeargs.kotlin)
    alias(libs.plugins.ksp)
}

android {
    namespace = "edu.iesam.loginexam1eval"
    compileSdk = 35

    defaultConfig {
        applicationId = "edu.iesam.loginexam1eval"
        minSdk = 26
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"
    }

    buildTypes {

    }
    buildFeatures {
        viewBinding = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    //Librerías para la app
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.gson.serializer)
    implementation(libs.viewmodel.scope)
    implementation(libs.nav.ui.ktx)
    implementation(libs.nav.fragment.ktx)

    //Librería de DI (Koin)
    implementation(project.dependencies.platform(libs.koin.bom))
    implementation(libs.koin.android)
    implementation(libs.koin.annotations)
    ksp(libs.koin.ksp)

    //Librerías para testing
    testImplementation(libs.junit.api)
    testRuntimeOnly(libs.junit.engine)
    testImplementation(libs.test.coroutines)
    testImplementation(libs.test.coroutines.android)
    testImplementation(libs.junit.api)
}