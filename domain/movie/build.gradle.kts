plugins {
    id("oscar.jvm.library")
    id("oscar.di.library.compose")
}

dependencies {
    implementation(project(":domain:region"))
    implementation(libs.kotlinx.coroutines.core)
}