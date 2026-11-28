# EGO deployment repair — 2.1.2

- Replaced `file:///android_asset/` loading with AndroidX WebViewAssetLoader (`https://appassets.androidplatform.net/assets/`).
- Disabled broad file/content access; bundled assets are served through the WebView asset handler. No INTERNET permission was added; app remains offline-only.
- Added Android 15 edge-to-edge system-bar insets to the root container.
- Added WebView console, resource-load, JavaScript error and unhandled-promise diagnostics.
- Hardened Little Artist canvas loading, pointer handling and pixel-fill error reporting; kept `touch-action: none`.
- Applied the user's exact 16 African Culture labels and paired each label with the corresponding image file.
- Synchronized the embedded `pictureCatalog` in `index.html` with `catalog_manifest.json`; verified all 16 referenced image files exist and both catalogues match in order.
- Version bumped to 2.1.2 / versionCode 6.

Build validation still needs to be run in an Android SDK/Gradle environment, followed by physical-device tests.
