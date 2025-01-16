plugins {
    id("oscar.jvm.library")
}

dependencies {
    implementation(project(":domain:region"))
    implementation(libs.kotlinx.coroutines.core)
}