# EGO complete upgrade candidate

This candidate is based on the supplied EGO updated source archive. It preserves the approved homepage and existing module artwork.

## Included
- 36 individual 3D digit and alphabet PNGs.
- 90 SVG colouring pictures, with tap-to-fill implementation.
- Corrected answer normalization and per-question answer lock.
- 100 unique three-letter words and 100 unique four-letter words, plus 26 two-letter words.
- 16 new transparent 3D object illustrations (fruit, animals, transport and familiar objects) used in story, shop, world and game cards.
- Fix: changing a colouring swatch no longer resets the current picture.
- Correct-answer feedback uses the Android system acknowledgement tone; no crowd-cheer recording is bundled or downloaded.

## Validation
The JavaScript syntax is checked with Node.js; word-bank counts, asset references, image files and SVG XML are checked in the packaging validation. An emulator/device interaction test is still required for final acceptance.

## Version 2.1.3 audio and phonics
- All spoken teaching prompts use the installed Android offline English system voice.
- Added the traditional 44-sound British English phoneme lesson.
- Removed generated speech clips and corrected the previous schwa-added phonics approximations.
