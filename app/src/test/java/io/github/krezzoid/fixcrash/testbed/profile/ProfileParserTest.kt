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
        // The backend omits the "avatar" member entirely. Gson bypasses the Kotlin
        // constructor null check, so ProfileDto.avatar is null despite its non-null type.
        val json = """
            {
              "id": "synthetic-id",
              "displayName": "Synthetic Name"
            }
        """.trimIndent()

        val profile = ProfileParser().parse(json)

        assertEquals("synthetic-id", profile.id)
    }
}
