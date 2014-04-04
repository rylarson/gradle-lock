package org.w007.gradle.lock

import org.gradle.api.Project

import org.junit.Test

import static org.junit.Assert.*
import static TestUtil.*
import static org.hamcrest.CoreMatchers.*

/**
 * Created by rlarson on 4/4/14.
 */

class LockPluginTest {
    @Test
    void canApplyToProject() {
        Project p = createProjectAndApplyPlugin(LockPlugin)
        assertThat(p.plugins.hasPlugin(LockPlugin), is(true))
    }

    @Test
    void canApplyByName() {
        Project p = createProjectAndApplyPlugin('lock')
        assertThat(p.plugins.hasPlugin('lock'), is(true))
    }

}
