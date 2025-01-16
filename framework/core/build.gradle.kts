plugins {
    id("oscar.android.library")
    id("oscar.android.room")
    id("oscar.jvm.retrofit")
    id("oscar.di.library")
}

android {
    namespace = "com.oscar.moviesproject.framework.core"
}

dependencies {
    implementation(project(":framework:movie"))
}