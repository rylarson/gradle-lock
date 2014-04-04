package org.w007.gradle.lock

import org.gradle.api.Project
import org.gradle.testfixtures.ProjectBuilder

/**
 * Created by rlarson on 4/4/14.
 */
class TestUtil {
    static Project createProjectAndApplyPlugin(plugin = LockPlugin) {
        Project p = createProject()
        p.apply plugin: plugin
        p
    }

    static Project createProject() {
        ProjectBuilder.builder()
                .withProjectDir(new File("deleteMe").canonicalFile.parentFile)
                .build()
    }

}
