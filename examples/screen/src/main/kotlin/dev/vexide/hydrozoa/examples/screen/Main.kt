package dev.vexide.hydrozoa.examples.screen

import dev.vexide.hydrozoa.Peripherals
import dev.vexide.hydrozoa.Platform
import dev.vexide.hydrozoa.display.Color
import dev.vexide.hydrozoa.display.Rect
import dev.vexide.hydrozoa.display.Text

fun main() {
    val peripherals = Peripherals.take().orElseThrow()
    val display = peripherals.takeDisplay()

    val rect = Rect(20, 20, 100, 100)
    display.fill(rect, Color.WHITE)
    display.stroke(rect, Color.FUCHSIA)

    val text = Text("Nice to see you!", 80, 80)
    display.draw(text, Color.OLIVE)

    while (true) {
        Platform.yield()
    }
}
