package gui.compose

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import me.tatarka.inject.annotations.Inject

@Inject
class ComposeBodyFooter : ComposeBodyElement {

    @Composable
    override fun build(): Unit {
        BottomAppBar(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
            contentColor = MaterialTheme.colorScheme.onSurfaceVariant
        ) {
            // We use a Box spanning the full width to center the copyright text
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "© 2024 Wasm Bridge. All rights reserved.",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}