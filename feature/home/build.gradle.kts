plugins {
    id("oscar.android.feature")
}

android {
    namespace = "com.oscar.home"
}

dependencies {
    implementation(project(":domain:movie"))
}