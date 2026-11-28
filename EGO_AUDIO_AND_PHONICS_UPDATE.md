# EGO 2.1.3 — Audio and phonics update

## Teaching voice
- Spoken teaching prompts now use the Android device's installed offline English Text-to-Speech voice through the native Android bridge.
- Voice selection prefers offline en-GB, then offline en-US, then another installed offline English voice.
- Added a slower native TTS rate for word-blending prompts.
- Removed the generated number/word/blend MP3 speech clips because they are no longer used.
- Nursery-rhyme music remains music; the crowd-cheer success effect is replaced with a short Android system acknowledgement tone.

## Phonics
- Removed misleading schwa-added pseudo-sounds such as "buh", "puh", "tuh" and "guh".
- Word-part buttons now speak the complete word and direct the child to listen for the sound in context, rather than presenting a false isolated sound.
- Added a 44-sound British English reference lesson: 12 monophthongs, 8 diphthongs and 24 consonants, with IPA symbols, key words and child-friendly articulation tips.
- The traditional 44-sound model is an educational British-English reference; accents vary, and some diphthong distinctions are not maintained by every speaker.

## Important pronunciation note
Android Text-to-Speech can pronounce whole example words, but it cannot guarantee phonetically exact isolated phoneme recordings. The 44-sound lesson therefore uses the installed offline system voice for example words and displays the correct IPA target. Professionally recorded human phoneme samples would be the best future upgrade.

## Device requirement
For offline speech, the Android device must have an offline English voice installed under Android Settings → Text-to-speech output → Preferred engine / language / voice data. If no offline English voice is installed, speech will be unavailable until one is installed.
