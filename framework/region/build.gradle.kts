plugins {
    id("oscar.android.library")
}

android {
    namespace = "com.oscar.moviesproject.framework.region"
}

dependencies {
    implementation(project(":domain:region"))
    implementation(libs.play.services.location)
}