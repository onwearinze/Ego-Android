# EGO 2.1 — My Little Learning World

A fully offline-first Android preschool learning app using the approved EGO home-screen design language and a bundled 3D cartoon interpretation of Ego's supplied reference photo.

## Sections and content
- Number Explorer: 0–100; tens and units; 30 rounds each for addition, subtraction, multiplication and division; 30 number-game rounds.
- Sound Blending: 26 letters, 26 two-letter English words, 100 three-letter words, 100 four-letter words and syllable cards.
- Tracing Centre: A–Z, a–z, 0–9 and 25 simple sentences.
- Shape World: 30 shape concepts with two real-life examples each.
- Colour Adventure: 27 named colours, 15 familiar colour-mixing outcomes and seven rainbow colours.
- Story Builder: 20 animal stories and fables.
- My Little Classroom: 50 teacher-led lesson cards.
- Pattern Detective: 30 tests. Memory Master: 40 rounds. Little Maths Shop: 40 rounds. Listen and Do: 40 listening challenges. Little Artist: 90 local SVG colouring pages.

The APK bundles 418 local MP3 audio clips plus Android offline Text-to-Speech for lesson narration when an offline English voice is installed. No INTERNET permission, online services, advertising, trackers, accounts or cloud storage. Progress and parent sound settings are stored locally.

## Build
Application ID `com.ego.learning`; version `2.1.0`; min SDK 23; compile SDK 35. Build with `gradle assembleDebug`. The included debug keystore is for repeat testing only, not a public release signing key.

## Scope notes
Some activities are generated from reusable game mechanics and curated question pools; the activity counts represent rounds/challenges, not dozens of entirely separate game engines. Bundled narration uses the Android offline TTS voice; professional studio-recorded character voices are not included. A small number of generated activity templates and educational statements should receive early-years educator review before public distribution.

## Homepage artwork integration
The approved EGO artwork pack has been integrated into `app/src/main/assets/images/`.
The homepage now uses the supplied logo, star counter, settings icon, welcome panel,
EGO pose, Miss Ada/classmates cutout, wooden ledge, learning-module artwork,
classroom banner, and bottom-navigation artwork as separate image layers.
The original module navigation and learning activity logic remain in place.

## EGO Games update
- The Games area now contains five interactive, touch-first games: Alphabet Adventure, Number Quest, Sound Safari, Treasure Hunt, and Memory Match.
- Each game contains ten progressive rounds, immediate feedback, spoken encouragement, and star/progress recording. Games use local HTML/CSS/JavaScript and do not require an internet connection or additional image downloads.
- The launcher display name is now **EGO**. The new square EGO artwork is installed as the Android launcher icon at standard density sizes.
- Sound Safari currently uses spoken animal-call words through the app's speech engine. It does not yet include authentic recorded animal-effect audio.


See `EGO_UPDATE_NOTES.md` for the latest source changes, verification results, and the optional offline crowd-cheer audio asset.
