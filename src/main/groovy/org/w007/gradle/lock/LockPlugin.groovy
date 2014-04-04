package org.w007.gradle.lock

import groovy.json.JsonBuilder
import groovy.json.JsonOutput
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.Configuration
import org.gradle.api.artifacts.Dependency
import org.gradle.api.artifacts.ResolvableDependencies

/**
 * Created by rlarson on 4/4/14.
 */
class LockPlugin implements Plugin<Project> {
    File lockFile

    @Override
    void apply(Project project) {
        lockFile = project.file("Gradle.lock")

        project.configurations.all({ Configuration configuration ->
            configuration.incoming.afterResolve { ResolvableDependencies resolvableDependencies ->

                lockFile.withWriter { Writer writer ->
                    writer.write JsonOutput.prettyPrint (JsonOutput.toJson(resolvableDependencies.resolutionResult.allDependencies.collect {
                        [group: it.selected.componentId.group, name: it.selected.componentId.module, version: it.selected.componentId.version]
                    }))
                }
            }
        })
    }

    def buildJsonForConfiguration(Configuration configuration) {
        def builder = new JsonBuilder()
        def root = builder.people {
            person {
                firstName 'Guillame'
                lastName 'Laforge'
                // Named arguments are valid values for objects too
                address(
                        city: 'Paris',
                        country: 'France',
                        zip: 12345,
                )
                married true
                // a list of values
                conferences 'JavaOne', 'Gr8conf'
            }
        }
        builder
    }
}
