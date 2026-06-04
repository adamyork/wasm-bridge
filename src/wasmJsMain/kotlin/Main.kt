import io.github.oshai.kotlinlogging.KotlinLogging
import io.github.oshai.kotlinlogging.Level
import kotlinx.browser.document
import kotlinx.browser.window

private val logger = KotlinLogging.logger {}

fun main() {
    LogConfig.initialize(minimumLevel = Level.DEBUG)
    logger.info { "main called" }
    val readyState = document.readyState.toString()
    logger.info { "current DOM readyState: $readyState" }
    val component = AppConfig::class.create()
    val bodyHeader = component.bodyHeader
    val bodyMain = component.bodyMain
    val bodyFooter = component.bodyFooter
    if (readyState == "interactive" || readyState == "complete") {
        logger.info { "DOM already loaded. Initializing layout immediately" }
        bodyHeader.build()
        bodyMain.build()
        bodyFooter.build()
    } else {
        logger.info { "DOM not ready yet. Registering event listener." }
        window.addEventListener("DOMContentLoaded") {
            bodyHeader.build()
            bodyMain.build()
            bodyFooter.build()
        }
    }
}
