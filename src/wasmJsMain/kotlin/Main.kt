import androidx.compose.foundation.layout.*
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.ComposeViewport
import gui.WasmBridgeColorScheme
import gui.compose.ComposeBodyElement
import io.github.oshai.kotlinlogging.KotlinLogging
import io.github.oshai.kotlinlogging.Level
import kotlinx.browser.document
import kotlinx.browser.window

private val logger = KotlinLogging.logger {}

@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
fun main() {
    LogConfig.initialize(minimumLevel = Level.DEBUG)
    logger.info { "main called" }
    val readyState = document.readyState.toString()
    logger.info { "current DOM readyState: $readyState" }
    val component = AppConfig::class.create()
    val composeBodyHeader = component.composeBodyHeader
    val composeBodyMain = component.composeBodyMain
    val composeBodyFooter = component.composeBodyFooter
    val wasmBridgeColorScheme = component.wasmBridgeColorScheme
    if (readyState == "interactive" || readyState == "complete") {
        logger.info { "DOM already loaded. Initializing layout immediately" }
        buildGui(composeBodyHeader, composeBodyMain, composeBodyFooter, wasmBridgeColorScheme)
    } else {
        logger.info { "DOM not ready yet. Registering event listener." }
        window.addEventListener("DOMContentLoaded") {
            buildGui(composeBodyHeader, composeBodyMain, composeBodyFooter, wasmBridgeColorScheme)
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
private fun buildGui(
    composeBodyHeader: ComposeBodyElement,
    composeBodyMain: ComposeBodyElement,
    composeBodyFooter: ComposeBodyElement,
    wasmBridgeColorScheme: WasmBridgeColorScheme
) {
    ComposeViewport(
        viewportContainerId = "ComposeTarget"
    ) {
        MaterialTheme(
            colorScheme = wasmBridgeColorScheme.getScheme()
        ) {
            Scaffold(
                modifier = Modifier
                    .semantics { contentDescription = "Application scaffold" }
                    .testTag("app-scaffold"),
                topBar = {
                    composeBodyHeader.build()
                },
                bottomBar = {
                    composeBodyFooter.build()
                }
            ) { innerPadding ->
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .semantics { contentDescription = "Main page layout container" }
                        .testTag("app-main-layout")
                        .padding(innerPadding),
                    contentAlignment = Alignment.TopCenter
                ) {
                    Box(
                        modifier = Modifier
                            .widthIn(max = 800.dp)
                            .fillMaxWidth()
                            .semantics { contentDescription = "Main content max width container" }
                            .testTag("app-main-content-wrapper")
                    ) {
                        composeBodyMain.build()
                    }
                }
            }
        }
    }
}
