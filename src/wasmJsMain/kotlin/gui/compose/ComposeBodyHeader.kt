package gui.compose

import androidx.compose.foundation.clickable
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import external.ExampleLibProxy
import kotlinx.browser.window
import me.tatarka.inject.annotations.Inject

@Inject
class ComposeBodyHeader : ComposeBodyElement {

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun build(): Unit {
        TopAppBar(
            title = {
                // Your logo/link element
                Text(
                    text = "Wasm Bridge",
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.clickable {
                        // Handle navigation or set window.location.hash = "#"
                    }
                )
            },
            actions = {
                // Run your proxy logic safely within the composition lifecycle
                LaunchedEffect(Unit) {
                    val exampleLibProxy = ExampleLibProxy()
                    exampleLibProxy.invokeTestFunc()
                }

                val menuItems = listOf(
                    "One" to "#one",
                    "Two" to "#two",
                    "Three" to "#three"
                )

                // Dynamic item generation, completely handling the list rendering underneath
                for ((text, hash) in menuItems) {
                    TextButton(
                        onClick = {
                            // The framework automatically handles navigation without raw DOM appends
                            window.location.hash = hash
                        }
                    ) {
                        Text(text = text)
                    }
                }
            }
        )
    }
}