"""One-off generator for Play Store listing graphics (hi-res icon + feature
graphic), built to match the app's adaptive icon (teal #0B3D3A background,
white "A" glyph) instead of hand-crafting raster art. Not part of the app
build -- run manually if the assets under fastlane/metadata/ need a refresh.
"""
from PIL import Image, ImageDraw, ImageFont

TEAL = (11, 61, 58)
TEAL_DARK = (7, 43, 41)
WHITE = (255, 255, 255)

# Path data lifted from app/src/main/res/drawable/ic_launcher_foreground.xml
OUTER = [(54, 24), (82, 86), (70, 86), (63, 64), (45, 64), (38, 86), (26, 86)]
HOLE = [(54, 40), (59, 60), (49, 60)]


def glyph_points(points, scale, cx, cy, src_cx=54, src_cy=55):
    return [(cx + (x - src_cx) * scale, cy + (y - src_cy) * scale) for x, y in points]


def make_icon(path, size=512, bg=TEAL, fg=WHITE):
    img = Image.new("RGB", (size, size), bg)
    draw = ImageDraw.Draw(img)
    scale = (size * 0.5) / 62  # glyph bbox height is 62 units tall
    cx = cy = size / 2
    draw.polygon(glyph_points(OUTER, scale, cx, cy), fill=fg)
    draw.polygon(glyph_points(HOLE, scale, cx, cy), fill=bg)
    img.save(path, "PNG")


def make_feature_graphic(path, w=1024, h=500):
    img = Image.new("RGB", (w, h), TEAL)
    draw = ImageDraw.Draw(img)
    for x in range(w):
        t = x / w
        r = int(TEAL[0] + (TEAL_DARK[0] - TEAL[0]) * t)
        g = int(TEAL[1] + (TEAL_DARK[1] - TEAL[1]) * t)
        b = int(TEAL[2] + (TEAL_DARK[2] - TEAL[2]) * t)
        draw.line([(x, 0), (x, h)], fill=(r, g, b))

    mark_scale = (h * 0.42) / 62
    mark_cx, mark_cy = h * 0.62, h * 0.5
    draw.polygon(glyph_points(OUTER, mark_scale, mark_cx, mark_cy), fill=WHITE)
    draw.polygon(glyph_points(HOLE, mark_scale, mark_cx, mark_cy), fill=TEAL)

    try:
        title_font = ImageFont.truetype("segoeuib.ttf", 88)
        sub_font = ImageFont.truetype("segoeui.ttf", 30)
    except OSError:
        title_font = ImageFont.load_default()
        sub_font = ImageFont.load_default()

    text_x = h * 0.62 + h * 0.30
    max_width = w - text_x - 40

    draw.text((text_x, h * 0.30), "Avika", font=title_font, fill=WHITE, anchor="lm")

    words = "Clinics, sensory-friendly places & schemes for Bengaluru families".split(" ")
    lines, current = [], ""
    for word in words:
        candidate = f"{current} {word}".strip()
        if draw.textlength(candidate, font=sub_font) <= max_width:
            current = candidate
        else:
            lines.append(current)
            current = word
    if current:
        lines.append(current)

    line_height = 44
    start_y = h * 0.58
    for i, line in enumerate(lines):
        draw.text(
            (text_x, start_y + i * line_height),
            line,
            font=sub_font,
            fill=(214, 233, 230),
            anchor="lm",
        )
    img.save(path, "PNG")


if __name__ == "__main__":
    import pathlib

    out = pathlib.Path(__file__).resolve().parent.parent / "fastlane" / "metadata" / "android" / "en-US" / "images"
    out.mkdir(parents=True, exist_ok=True)
    make_icon(str(out / "icon.png"))
    make_feature_graphic(str(out / "featureGraphic.png"))
    print("Wrote", out / "icon.png", "and", out / "featureGraphic.png")
