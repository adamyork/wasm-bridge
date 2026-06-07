package gui

import androidx.compose.runtime.Composable
import org.w3c.dom.Element

interface DualBodyComponent {

    @Composable
    fun buildCompose(): Unit

    fun buildSr(): Element?

}