# EGO update notes

## Included in this source update
- Preserved the existing EGO homepage artwork, module artwork, classroom assets, colours and navigation design.
- Added the 36 supplied 3D digit/alphabet PNGs to `app/src/main/assets/images/learning/`.
- Number cards 0–100 now compose their visible digits from the supplied 3D digit assets.
- Alphabet cards A–Z now show the supplied paired uppercase/lowercase 3D artwork.
- Added the 40 supplied SVG colouring illustrations alongside the original 50: 90 colouring pictures total.
- Replaced the freehand-only colouring overlay with enclosed-region tap-to-fill colouring. Open background regions are deliberately ignored to prevent filling the whole page.
- Normalized answer comparison to avoid string/number and case/whitespace mismatches.
- Exposed every function called by inline game controls to the WebView global scope; several game buttons previously referenced functions hidden inside the JavaScript closure.
- Correct-answer feedback now attempts to play `audio/crowd-cheer.mp3`; if that optional file is not present, a short celebratory chime plays instead.
- Corrected the Sound Blending word banks to exactly 100 unique three-letter words and 100 unique four-letter words. The 26 two-letter words are retained.

## Crowd cheer audio still to add
The supplied project did not contain a crowd-cheer recording. To keep the app offline-first, download the short CC0 cheer from:
https://sfxmint.com/dl/crowd-chant-15.mp3

Save it as:
`app/src/main/assets/audio/crowd-cheer.mp3`

Source page and CC0 licence information:
https://sfxmint.com/sounds/crowd-chant-15

Until that file is added, the app safely plays the built-in celebratory chime. No internet permission or online audio dependency has been introduced.

## Voice
The app currently uses its bundled MP3 word/number clips where available and the device's installed offline English Text-to-Speech voice for other phrases. The uploaded project does not document the recording provenance of the 418 MP3 clips, so they are not labelled as verified human recordings. An installed offline voice is required for the native TTS fallback.

## Verification performed
- JavaScript syntax check passed with Node.js.
- All 40 newly added SVG files parsed and rendered successfully during asset validation.
- All 90 colouring picture paths resolve in the project.
- All 10 digit and 26 alphabet PNG paths resolve.
- Word bank counts checked: 26 two-letter, 100 three-letter and 100 four-letter entries.
- An Android APK build and on-device interaction test were not possible in this environment because the Android SDK/Gradle build environment and a connected Android device are not available. Use the included GitHub Actions workflow or Android Studio to build and then test on the target phone before release.


## 33-sheet illustration integration
- Added all 33 newly supplied 3D illustration sheets to the source project.
- Extracted 528 individual illustrations from the 4-by-4 sheets; 4 exact/perceptual duplicates were removed, leaving 524 catalogue assets.
- Stored individual transparent PNG assets under `app/src/main/assets/images/catalog/`, grouped by subject category.
- Added the offline Picture Explorer to Wonderful World, with image, subject name, category, spoken-name control, and previous/next navigation.
- Updated the shared illustration renderer so matching objects in existing animal, nature, story, shop, and treasure activities can use the new 3D artwork instead of emoji fallbacks.
- Added a machine-readable catalogue manifest at `app/src/main/assets/images/catalog-manifest.json`.
- No network access is required for these assets. The project remains offline-first.

## Validation for this integration
- 33 source sheets identified and processed.
- 524 individual PNG illustrations packaged.
- Inline JavaScript syntax check passed with Node.js.
- All catalogue paths are checked against files during packaging.
- Android runtime rendering and touch interaction still require installation and testing on the target device.
