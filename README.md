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


See `EGO_AUDIO_AND_PHONICS_UPDATE.md` for the current system-voice and phonics changes.


## Categorised illustration catalog

The latest non-APK build includes the 467 extracted illustration assets under
`app/src/main/assets/images/catalog/`, grouped into category folders. The in-app
Picture Explorer is generated from this catalog and uses the actual category
paths. The HTML image resolver also uses these catalog paths for matching
learning illustrations and activity-menu art where a relevant image exists.


## Catalog and learning-page refinements (27 Nov 2026)
- Removed the `unidentified_review` category; its remaining wind and baseball assets are represented in their correct categories.
- Removed 27 cross-category duplicate object entries, retaining one canonical category copy for each repeated object.
- Updated the in-app picture catalogue and image lookup to reflect the 438 retained catalog images.
- Reduced Miss Ada's portrait display size by approximately half.
- Added a separate Back control on module menus and learning pages; the Previous/Next lesson controls remain unchanged.
- Applied a rounded system-font stack to page headings without adding an online font dependency.
- Fixed Memory Master so it locks after two cards are selected, keeps matched pairs, and turns both unmatched cards back over after a short delay. Memory cards now use catalog images.


## Nursery Rhymes Update
A dedicated Nursery Rhymes module now includes the five supplied songs. The supplied `background_sound.mp3` replaces the previous generated background tone and is controlled by the parent music setting. See `EGO_NURSERY_RHYMES_UPDATE.md`.


## Version 2.1.3
- Spoken teaching prompts use the installed Android offline English TTS voice.
- Added a 44-phoneme British English lesson and corrected phonics teaching away from schwa-added pseudo-sounds.
