plugins {
    id("oscar.android.library")
    id("oscar.android.room")
    id("oscar.jvm.retrofit")
}

android {
    namespace = "com.oscar.moviesproject.framework.core"
}

dependencies {
    implementation(project(":framework:movie"))
}