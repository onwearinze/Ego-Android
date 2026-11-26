# EGO complete upgrade candidate

This candidate is based on the supplied EGO updated source archive. It preserves the approved homepage and existing module artwork.

## Included
- 36 individual 3D digit and alphabet PNGs.
- 90 SVG colouring pictures, with tap-to-fill implementation.
- Corrected answer normalization and per-question answer lock.
- 100 unique three-letter words and 100 unique four-letter words, plus 26 two-letter words.
- 16 new transparent 3D object illustrations (fruit, animals, transport and familiar objects) used in story, shop, world and game cards.
- Fix: changing a colouring swatch no longer resets the current picture.
- Correct-answer sound playback attempts `audio/crowd-cheer.mp3` and falls back to a local celebratory chime.

## Crowd cheer sound
The project archive does not contain `app/src/main/assets/audio/crowd-cheer.mp3`. Add it in Termux using the CC0 public-domain MP3 from SFXMint:

```sh
curl -L --fail --retry 3 'https://sfxmint.com/dl/crowd-chant-15.mp3' -o app/src/main/assets/audio/crowd-cheer.mp3
```

SFXMint describes this 5.2-second cheer as AI-generated, CC0 1.0, suitable for commercial apps and redistribution. It is not a human-recorded crowd recording. The existing bundled MP3 narration has no verified recording provenance in the supplied source; do not advertise it as professionally human-recorded. Android offline TTS uses the English voice installed on the device.

## Validation
The JavaScript syntax is checked with Node.js; word-bank counts, asset references, image files and SVG XML are checked in the packaging validation. An emulator/device interaction test is still required for final acceptance.
