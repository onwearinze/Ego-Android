# EGO UI and Asset Fixes — 27 November 2026

Applied to the existing EGO Nursery Rhymes source package.

## Changes
- Replaced the homepage classroom-character image slot with the independently supplied Miss Ada portrait (`images/miss-ada.png`) so her portrait is visible independently of the group artwork.
- Removed Nursery Rhymes from the homepage module grid.
- Added a dedicated Nursery Rhymes / Sing Along bar immediately below the wooden ledge and above the modules section. It opens the existing `rhymes` section and preserves the five existing songs and player.
- Added safe transparent margins to all 68 currently included Shape World images (31 2D and 37 3D) to prevent the artwork from touching/clipping against its display bounds. No additional shapes were added.
- Explicitly uppercased the capital-letter tracing value and tracing guide, and placed the capital-A object illustration above the tracing canvas.
- Replaced the capital-A object image with the reattached capital-A artwork from the objects sheet.

## Validation
- Inline JavaScript syntax: `node --check` passed.
- Static local `src` / `href` references in `index.html`: no missing files.
- Shape manifest: 68 entries (31 2D, 37 3D).
- No Android APK build or on-device runtime test was performed in this environment; this package is the updated Android project source.
