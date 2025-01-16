plugins {
    id("oscar.android.library")
    id("oscar.android.room")
    id("oscar.jvm.retrofit")
}

android {
    namespace = "com.oscar.moviesproject.framework.movie"
}

dependencies {
    implementation(project(":domain:movie"))
}