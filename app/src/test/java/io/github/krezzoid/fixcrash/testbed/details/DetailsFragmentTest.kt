package io.github.krezzoid.fixcrash.testbed.details

import java.lang.reflect.InvocationTargetException
import org.junit.Test

class DetailsFragmentTest {

    /**
     * The delayed Runnable posted in onViewCreated fires after the user left the details
     * screen. At that point the fragment has no host anymore, and onDetailsLoaded calls
     * requireContext(), which throws IllegalStateException("... not attached to a context.").
     *
     * There is no Robolectric in this module and the fragment's Handler field initializer
     * needs a real main Looper, so the instance is allocated without running its constructor.
     * That yields a fragment with no host: the same state as one that was detached.
     */
    @Test
    fun delayedDetailsLoadAfterDetachCrashes() {
        val fragment = allocateWithoutConstructor(DetailsFragment::class.java)

        // Simulate the posted Runnable firing after the fragment was detached.
        val onDetailsLoaded = DetailsFragment::class.java.getDeclaredMethod("onDetailsLoaded")
        onDetailsLoaded.isAccessible = true
        try {
            onDetailsLoaded.invoke(fragment)
        } catch (e: InvocationTargetException) {
            throw e.targetException
        }
    }

    private fun <T> allocateWithoutConstructor(type: Class<T>): T {
        val unsafeClass = Class.forName("sun.misc.Unsafe")
        val field = unsafeClass.getDeclaredField("theUnsafe")
        field.isAccessible = true
        val unsafe = field.get(null)
        @Suppress("UNCHECKED_CAST")
        return unsafeClass.getMethod("allocateInstance", Class::class.java).invoke(unsafe, type) as T
    }
}
