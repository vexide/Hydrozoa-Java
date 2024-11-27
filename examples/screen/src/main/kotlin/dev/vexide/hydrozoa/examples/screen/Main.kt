package dev.vexide.hydrozoa.examples.screen

import dev.vexide.hydrozoa.Peripherals
import dev.vexide.hydrozoa.Platform
import dev.vexide.hydrozoa.display.*

fun main() {
    val peripherals = Peripherals.take().orElseThrow()
    val display = peripherals.takeDisplay()

    val rect = Rect(20, 20, 100, 100)
    display.fill(rect, Color.WHITE)
    display.stroke(rect, Color.FUCHSIA)

    val circle = Circle(80, 80, 40)
    display.stroke(circle, Color.BLUE)

    var text = Text("Nice to see you!", 80, 40)
    display.draw(text, Color.OLIVE)

    text = Text("This is Hydrozoa.", 80, 80, Font(Font.Size.LARGE, Font.Family.PROPORTIONAL))
    display.draw(text, Color.RED, Color.SILVER)

    while (true) {
        Platform.yield()
    }
}
