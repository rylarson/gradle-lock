package org.w007.gradle.lock

import groovy.json.JsonSlurper
import org.gradle.api.Project
import org.junit.Test

import static TestUtil.*
import static org.junit.Assert.*
import static org.hamcrest.CoreMatchers.*

/**
 * Created by rlarson on 4/4/14.
 */
class LockPluginIntegrationTest {
    @Test
    void lockerWritesResolvedVersionsToFile() {
        Project p = createProjectAndApplyPlugin('lock')
        p.configure(p) {
            configurations {
                compile
            }
            repositories {
                mavenCentral()
            }
            dependencies {
                compile 'junit:junit:4.+'
            }
        }
        def resolved = p.configurations.compile.resolvedConfiguration.resolvedArtifacts
        File lockFile = p.plugins.getPlugin('lock').lockFile
        assertThat(lockFile.exists(), is(true))

        def json = new JsonSlurper().parseText(lockFile.text)
        assertThat(json[0].version, equalTo('4.11'))
    }

}
