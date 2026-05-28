import kotlinx.browser.document
import kotlinx.browser.window

fun main() {
    println("main called ")
    val readyState = document.readyState.toString()
    println("Current DOM readyState: $readyState")
    if (readyState == "interactive" || readyState == "complete") {
        println("DOM already loaded. Initializing layout immediately.")
        buildUI()
    } else {
        println("DOM not ready yet. Registering event listener.")
        window.addEventListener("DOMContentLoaded", {
            buildUI()
        })
    }
}