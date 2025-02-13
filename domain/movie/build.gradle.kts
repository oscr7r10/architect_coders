plugins {
    id("oscar.jvm.library")
    id("oscar.di.library")
}

dependencies {
    implementation(project(":domain:region"))
    testImplementation(project(":test:unit"))
}