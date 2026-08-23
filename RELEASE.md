# Releasing Avika to the Play Store

This is the state of Play Store readiness as of this commit, and the steps
still left to a human (signing keys, Play Console UI, and anything that
needs an actual device/emulator to verify — none of which can be done from
this environment).

## What's already done in this repo

- `app/build.gradle.kts`: `compileSdk`/`targetSdk` bumped to **36** (Android
  16), AGP bumped to **9.0.1**, Kotlin to **2.2.10**, and the Compose
  compiler moved to the `org.jetbrains.kotlin.plugin.compose` Gradle plugin
  (the old `composeOptions.kotlinCompilerExtensionVersion` DSL doesn't work
  with Kotlin 2.x). `gradle.properties` opts out of AGP 9's "built-in
  Kotlin" default (`android.builtInKotlin=false`) so the project keeps using
  the explicit `org.jetbrains.kotlin.android` plugin instead of migrating to
  the new plugin-less DSL.
- Gradle wrapper bumped to **9.1.0** (the minimum AGP 9.0.1 requires).
- `AndroidManifest.xml`: added `android:enableOnBackInvokedCallback="true"`
  for predictive back, matching the targetSdk 36 baseline.
- Privacy policy (`docs/privacy-policy.html`) rewritten in an earlier commit
  to match reality (the app collects nothing), and the contact-email
  placeholder is now filled in.
- `docs/` (renamed from `prototype/`) holds the privacy policy and design
  prototype as plain static HTML, ready for GitHub Pages.
- `fastlane/metadata/android/en-US/` has a first pass at the store listing:
  `title.txt`, `short_description.txt`, `full_description.txt`, and
  `images/icon.png` (512×512) + `images/featureGraphic.png` (1024×500),
  generated from the app's actual launcher-icon glyph so they match the
  real app icon. `images/phoneScreenshots/` is empty — Play Console
  requires at least 2 real device screenshots, which need an emulator or
  phone to capture (see "Still to do" below).

**⚠️ Important — I could not compile or run this project.** This sandbox
has no JDK, no Android SDK, and no emulator, so none of the Gradle/Kotlin
version changes above have been build-verified. AGP 9 / Kotlin 2.2 is a
real, documented, but non-trivial jump from AGP 8.5 / Kotlin 1.9. **Before
you rely on this for a release, open the project in a recent Android Studio
and let it sync** — that's the first real build this configuration will
see. If sync fails, the two likeliest culprits are the Compose compiler
plugin wiring in `app/build.gradle.kts` or a library version in
`gradle/libs.versions.toml` that's since been superseded.

## Still to do (needs a human + a build environment)

### 1. Generate your upload keystore

Never share this file or its passwords with anyone, including in chat/AI
tools — treat it like a password to your entire app's identity on Play.

```sh
keytool -genkeypair -v -keystore keystore/avika-upload.jks \
  -alias avika-upload -keyalg RSA -keysize 2048 -validity 10000
```

Then copy `keystore.properties.example` to `keystore.properties` at the
repo root and fill in the real `storePassword`/`keyPassword` you just set.
Both `keystore/` and `keystore.properties` are already gitignored — never
commit them.

### 2. Build the release bundle

```sh
./gradlew bundleRelease
```

Output: `app/build/outputs/bundle/release/app-release.aab`. This is the
file you upload to Play Console (not an APK).

### 3. Take real screenshots

Run the app on an emulator or device (phone, and ideally a 7"/10" tablet)
and capture at least 2 screenshots per form factor for
`fastlane/metadata/android/en-US/images/phoneScreenshots/`. Play Console
requires these before it will let you publish.

### 4. Host the privacy policy

`docs/privacy-policy.html` is ready to serve, but this token doesn't have
admin rights on the `KernelLex/Avika` repo to flip Settings → Pages on. Ask
whoever does to enable **Settings → Pages → Source: Deploy from a branch →
`main` / `/docs`**. It'll then be live at
`https://kernellex.github.io/Avika/privacy-policy.html` — that's the URL
Play Console's "Privacy policy" field wants.

### 5. Decide on `targetSdk` risk

Google requires **new apps to target API 36 by Aug 31, 2026** (an
extension to Nov 1, 2026 is available in Play Console if you need it). This
repo now targets 36, but since I couldn't verify the build, if Android
Studio surfaces problems you can't resolve before the deadline, targeting
**35** (`app/build.gradle.kts` → `compileSdk`/`targetSdk = 35`, and revert
AGP to `8.7.0` + Gradle wrapper to `8.9`, no Kotlin/Compose-plugin changes
needed) is a safe fallback that still satisfies Play's baseline for
existing apps and buys time.

## Play Console walkthrough

1. **Create the app** — [play.google.com/console](https://play.google.com/console) → *Create app* → name "Avika", default language English (India), app type "App", free, confirm declarations.
2. **Set up your app** (left sidebar, "Set up your app"):
   - **App access** — all functionality available without special access (no login).
   - **Ads** — no ads.
   - **Content rating** — fill the questionnaire (category: Reference/Utility or Lifestyle). Nothing in Avika collects data, has UGC, or shows mature content, so this should land as "Everyone."
   - **Target audience** — 18+ (it's a tool for parents/caregivers, not children).
   - **News app** — no.
   - **Data safety** — answer "No data collected" throughout; Avika makes no network calls and has no analytics/ads SDKs. Link the privacy policy URL from step 4 above.
   - **Government apps** — no (Avika isn't a government app, just references government schemes).
   - **Financial features** — no.
   - **Health app declaration** — you'll likely be asked since it references clinics/health info; answer based on Avika being a directory/informational app, not a health-data-collecting app (it stores nothing).
3. **Store listing** (Main store listing):
   - Paste in `title.txt`, `short_description.txt`, `full_description.txt` from `fastlane/metadata/android/en-US/`.
   - Upload `images/icon.png` and `images/featureGraphic.png`.
   - Upload phone screenshots (step 3 above).
   - App category: **Medical** or **Lifestyle** (pick whichever fits your judgment — Medical requires the health-app declarations above to be accurate).
   - Contact details: email `amoghprashanth158@gmail.com`, privacy policy URL from step 4.
4. **Production release** (Release → Production → Create new release):
   - Upload `app-release.aab` from step 2.
   - Play App Signing: accept Google's managed signing (recommended) — Google re-signs your upload key with its own, so losing your upload keystore later is recoverable via a reset request; only the initial keystore matters going forward.
   - Fill release notes (e.g. "Initial release: clinic directory, sensory-friendly venues map, and schemes & UDID guide for Bengaluru families.").
   - Save → Review release → if all sections above are green, **Submit for review**.
5. **Wait for review** — first-time app reviews typically take a few hours to a few days. You'll get an email either way; if rejected, Play Console tells you exactly which policy and section to fix.

Once approved, the app goes live on the Play Store automatically (or on a
staged rollout percentage if you configured one).
