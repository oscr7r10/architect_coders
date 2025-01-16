plugins {
    id("oscar.android.library.compose")
}

android {
    namespace = "com.oscar.common"
}

dependencies {
    implementation(project(":domain:movie"))
    implementation(libs.androidx.activity.compose)
}