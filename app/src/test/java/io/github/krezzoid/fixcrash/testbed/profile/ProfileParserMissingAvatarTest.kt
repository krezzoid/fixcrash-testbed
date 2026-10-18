package io.github.krezzoid.fixcrash.testbed.profile

import org.junit.Test

class ProfileParserMissingAvatarTest {

    @Test
    fun parsesProfileWithoutAvatar() {
        val json = """{"id":"user-1234","displayName":"Sample Person"}"""

        ProfileParser().parse(json)
    }
}
