plugins {
    id("oscar.jvm.library")
    id("oscar.di.library")
}

dependencies {
    implementation(project(":domain:region"))
    implementation(libs.kotlinx.coroutines.core)
}