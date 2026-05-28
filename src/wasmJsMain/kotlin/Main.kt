import kotlinx.browser.document
import kotlinx.browser.window
import service.RandomNumberService

fun main() {
    println("main called ")
    val readyState = document.readyState.toString()
    println("Current DOM readyState: $readyState")
    val bodyHeader = BodyHeader()
    val bodyMain = BodyMain()
    val bodyFooter = BodyFooter()
    val randomNumberService = RandomNumberService()
    if (readyState == "interactive" || readyState == "complete") {
        println("DOM already loaded. Initializing layout immediately.")
        bodyHeader.buildUI()
        bodyMain.buildUI(randomNumberService)
        bodyFooter.buildUI()
    } else {
        println("DOM not ready yet. Registering event listener.")
        window.addEventListener("DOMContentLoaded", {
            bodyHeader.buildUI()
            bodyMain.buildUI(randomNumberService)
            bodyFooter.buildUI()
        })
    }
}
