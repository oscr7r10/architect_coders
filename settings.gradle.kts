pluginManagement {
    includeBuild("build-logic")
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "MoviesProject"
include(":app")

include(":domain:movie")
include(":domain:region")

include(":framework:core")
include(":framework:movie")
include(":framework:region")

include(":feature:common")
include(":feature:detail")
include(":feature:home")
include(":framework:core1")
include(":test:unit")
