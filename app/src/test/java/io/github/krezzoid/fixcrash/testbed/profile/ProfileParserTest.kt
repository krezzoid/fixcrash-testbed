package io.github.krezzoid.fixcrash.testbed.profile

import org.junit.Assert.assertEquals
import org.junit.Test

class ProfileParserTest {

    @Test
    fun parsesFullProfile() {
        val json = """
            {
              "id": "user-0002",
              "displayName": "Another User",
              "avatar": {"url": "https://example.com/a.png", "width": 64, "height": 64}
            }
        """.trimIndent()

        val profile = ProfileParser().parse(json)

        assertEquals(Profile("user-0002", "Another User", "https://example.com/a.png"), profile)
    }

    @Test
    fun parsesProfileWithoutAvatar() {
        val json = """
            {
              "id": "user-0003",
              "displayName": "Test User"
            }
        """.trimIndent()

        ProfileParser().parse(json)
    }
}
