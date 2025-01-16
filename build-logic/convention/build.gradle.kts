plugins {
    `kotlin-dsl`
}

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.kotlin.gradlePlugin)
}

gradlePlugin {
    plugins{
        register("androidApplication"){
            id = "oscar.android.application"
            implementationClass = "AndroidApplicationConventionPlugin"
        }
        register("androidApplicationCompose"){
            id = "oscar.android.application.compose"
            implementationClass = "AndroidApplicationComposeConventionPlugin"
        }
        register("androidLibrary"){
            id = "oscar.android.library"
            implementationClass = "AndroidLibraryConventionPlugin"
        }
        register("androidLibraryCompose"){
            id = "oscar.android.library.compose"
            implementationClass = "AndroidLibraryComposeConventionPlugin"
        }
        register("androidFeature"){
            id = "oscar.android.feature"
            implementationClass = "AndroidFeatureConventionPlugin"
        }
        register("jvmLibrary"){
            id = "oscar.jvm.library"
            implementationClass = "JvmLibraryConventionPlugin"
        }
        register("androidRoom"){
            id = "oscar.android.room"
            implementationClass = "AndroidRoomConventionPlugin"
        }
        register("jvmRetrofit"){
            id = "oscar.jvm.retrofit"
            implementationClass = "JvmRetrofitConventionPlugin"
        }
    }
}