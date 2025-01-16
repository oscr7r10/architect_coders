plugins {
    id("oscar.android.feature")
}

android {
    namespace = "com.oscar.detail"
}

dependencies {
    implementation(project(":domain:movie"))
}