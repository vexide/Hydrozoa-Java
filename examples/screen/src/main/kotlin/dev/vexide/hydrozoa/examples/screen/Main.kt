package dev.vexide.hydrozoa.examples.screen

import dev.vexide.hydrozoa.Peripherals
import dev.vexide.hydrozoa.Platform
import dev.vexide.hydrozoa.display.Rect
import dev.vexide.hydrozoa.display.Rgb
import dev.vexide.hydrozoa.display.Text

fun main() {
    val peripherals = Peripherals.take().orElseThrow()
    val display = peripherals.takeDisplay()

    val rect = Rect(20, 20, 100, 100)
    display.fill(rect, Rgb.fromInteger(0xFFFFFF))
    display.stroke(rect, Rgb.fromInteger(0xFF00FF))

    val text = Text("Nice to see you!", 80, 80)
    display.write(text, Rgb.fromInteger(0x00FFFF), Rgb.fromInteger(0x000000))

    while (true) {
        Platform.yield()
    }
}
