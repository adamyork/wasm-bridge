package gui.compose

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.*
import androidx.compose.ui.unit.dp

class ComposeScreenLayer {

    @Composable
    fun build() {
        var x by remember { mutableFloatStateOf(0f) }
        var y by remember { mutableFloatStateOf(0f) }
        val focusRequester = remember { FocusRequester() }

        Canvas(
            modifier = Modifier
                .size(300.dp)
                .focusRequester(focusRequester)
                .focusable()
                .onKeyEvent { keyEvent ->
                    if (keyEvent.type == KeyEventType.KeyDown) {
                        // 2. Update state instead of local variables
                        when (keyEvent.key) {
                            Key.DirectionUp -> y -= 10f
                            Key.DirectionDown -> y += 10f
                            Key.DirectionLeft -> x -= 10f
                            Key.DirectionRight -> x += 10f // Fixed typo: was DirectionDown
                            else -> return@onKeyEvent false
                        }
                        true // Event consumed
                    } else false
                }
        ) {
            // 3. The Canvas reads the current state values automatically
            drawCircle(
                color = Color.Red,
                radius = 8f,
                center = Offset(x + 8f, y + 8f)
            )
        }

// 4. Request focus so the component is ready for input immediately
        LaunchedEffect(Unit) {
            focusRequester.requestFocus()
        }
    }
}
