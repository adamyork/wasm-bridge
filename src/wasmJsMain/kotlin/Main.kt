import kotlinx.browser.document
import kotlinx.browser.window

fun main() {
    println("main called ")
    val readyState = document.readyState.toString()
    println("Current DOM readyState: $readyState")
    val component = AppConfig::class.create()
    val bodyHeader = component.bodyHeader
    val bodyMain = component.bodyMain
    val bodyFooter = component.bodyFooter
    if (readyState == "interactive" || readyState == "complete") {
        println("DOM already loaded. Initializing layout immediately.")
        bodyHeader.build()
        bodyMain.build()
        bodyFooter.build()
    } else {
        println("DOM not ready yet. Registering event listener.")
        window.addEventListener("DOMContentLoaded", {
            bodyHeader.build()
            bodyMain.build()
            bodyFooter.build()
        })
    }
}
