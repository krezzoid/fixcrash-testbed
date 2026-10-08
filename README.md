# fixcrash-testbed

A small Kotlin Android app: a profile, a paged feed and a details screen. Data comes from canned responses in `FakeBackend`.

## Build

Put the app's Firebase config at `app/google-services.json` (git ignores it), then:

```bash
./gradlew :app:assembleDebug
./gradlew :app:testDebugUnitTest
```

Release builds are signed with the debug key: the app is never published.
