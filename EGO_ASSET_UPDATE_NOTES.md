# EGO asset and Shape World update

- Integrated all 20 supplied 16:9 Story Builder illustrations under `app/src/main/assets/images/stories/`.
- Story Builder now displays its matching supplied illustration for each of the 20 stories.
- Replaced the previous Shape World artwork set with individually extracted transparent PNG assets for 31 unique 2D shapes and 37 unique 3D geometric solids (68 total), stored under `images/shapes/`.
- Shape cards display the extracted image and its explicit shape name; the existing real-life example labels remain.
- Added transparent object/icon assets from the supplied object sheet under `images/objects/` and mapped the related learning symbols to those local assets.
- Set Miss Ada's in-module portrait display to 62 x 62 CSS pixels to keep it clear and prominent without dominating the teacher panel.
- Retained the existing learning-page Back navigation and Previous/Next lesson navigation.
- Retained the existing Memory Master mismatch handling, which turns unmatched cards back over after the reveal interval and leaves matched pairs visible.
- The app remains offline-first; all added artwork is stored locally in app assets.
