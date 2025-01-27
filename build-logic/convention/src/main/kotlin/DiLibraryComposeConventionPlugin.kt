import com.oscar.architectcoders.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class DiLibraryComposeConventionPlugin: Plugin<Project> {
    override fun apply(target: Project) {
        with(target){
            with(pluginManager){
                apply("oscar.di.library")
                apply("dagger.hilt.android.plugin")
            }
            dependencies.add("implementation", libs.findLibrary("hilt.android").get())
            dependencies.add("implementation", libs.findLibrary("androidx.hilt.navigation.compose").get())
        }
    }
}