package gui.compose

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import me.tatarka.inject.annotations.Inject

@Inject
class ComposeBodyFooter : ComposeBodyElement {

    @Composable
    override fun build() {
        BottomAppBar(
            modifier = Modifier
                .semantics { contentDescription = "Page footer" }
                .testTag("compose-body-footer"),
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
            contentColor = MaterialTheme.colorScheme.onSurfaceVariant
        ) {
            // We use a Box spanning the full width to center the copyright text
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .semantics { contentDescription = "Footer content container" },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "© 2024 Wasm Bridge. All rights reserved.",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier
                        .semantics { contentDescription = "Copyright notice" }
                        .testTag("footer-copyright")
                )
            }
        }
    }
}
