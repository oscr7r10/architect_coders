plugins {
    id("oscar.android.feature")
    id("oscar.di.library.compose")
}

android {
    namespace = "com.oscar.detail"
}

dependencies {
    implementation(project(":domain:movie"))
}