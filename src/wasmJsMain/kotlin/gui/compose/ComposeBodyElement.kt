package gui.compose

import androidx.compose.runtime.Composable

interface ComposeBodyElement {

    @Composable
    fun build(): Unit

}