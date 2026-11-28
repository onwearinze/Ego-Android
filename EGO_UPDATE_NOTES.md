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
- Correct-answer feedback now uses the Android system acknowledgement tone. No crowd-cheer MP3 is downloaded or required.

## Crowd cheer audio still to add

## Voice
The app currently uses its bundled MP3 word/number clips where available and the device's installed offline English Text-to-Speech voice for other phrases. The uploaded project does not document the recording provenance of the 418 MP3 clips, so they are not labelled as verified human recordings. An installed offline voice is required for the native TTS fallback.

## Verification performed
- JavaScript syntax check passed with Node.js.
- All 40 newly added SVG files parsed and rendered successfully during asset validation.
- All 90 colouring picture paths resolve in the project.
- All 10 digit and 26 alphabet PNG paths resolve.
- Word bank counts checked: 26 two-letter, 100 three-letter and 100 four-letter entries.
- An Android APK build and on-device interaction test were not possible in this environment because the Android SDK/Gradle build environment and a connected Android device are not available. Use the included GitHub Actions workflow or Android Studio to build and then test on the target phone before release.
