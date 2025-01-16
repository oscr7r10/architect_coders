plugins {
    id("oscar.android.library")
    id("oscar.di.library")
}

android {
    namespace = "com.oscar.moviesproject.framework.region"
}

dependencies {
    implementation(project(":domain:region"))
    implementation(libs.play.services.location)
}