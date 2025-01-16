plugins {
    id("oscar.android.feature")
    id("oscar.di.library.compose")
}

android {
    namespace = "com.oscar.home"
}

dependencies {
    implementation(project(":domain:movie"))
}