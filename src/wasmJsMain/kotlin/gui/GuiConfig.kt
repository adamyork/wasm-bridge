package gui

import AppScope
import ComposeFooter
import ComposeHeader
import ComposeMain
import Footer
import Header
import Main
import gui.sr.BodyMain
import gui.compose.ComposeBodyMain
import gui.compose.ComposeBodyElement
import gui.compose.ComposeBodyFooter
import gui.compose.ComposeBodyHeader
import gui.compose.ComposeColorScheme
import gui.sr.BodyElement
import gui.sr.BodyFooter
import gui.sr.BodyHeader
import me.tatarka.inject.annotations.Provides

interface GuiConfig {

    @Header
    val bodyHeader: BodyElement

    @Main
    val bodyMain: BodyElement

    @Footer
    val bodyFooter: BodyElement

    @ComposeHeader
    val composeBodyHeader: ComposeBodyElement

    @ComposeMain
    val composeBodyMain: ComposeBodyElement

    @ComposeFooter
    val composeBodyFooter: ComposeBodyElement

    val wasmBridgeColorScheme: WasmBridgeColorScheme

    @AppScope
    @Provides
    @Header
    fun provideBodyHeader(impl: BodyHeader): BodyElement = impl

    @AppScope
    @Provides
    @Main
    fun provideBodyMain(impl: BodyMain): BodyElement = impl

    @AppScope
    @Provides
    @Footer
    fun provideBodyFooter(impl: BodyFooter): BodyElement = impl

    @AppScope
    @Provides
    @ComposeHeader
    fun provideComposeBodyHeader(impl: ComposeBodyHeader): ComposeBodyElement = impl

    @AppScope
    @Provides
    @ComposeMain
    fun provideComposeBodyMain(impl: ComposeBodyMain): ComposeBodyElement = impl

    @AppScope
    @Provides
    @ComposeFooter
    fun provideComposeBodyFooter(impl: ComposeBodyFooter): ComposeBodyElement = impl

    @AppScope
    @Provides
    fun provideWasmBridgeColorScheme(impl: WasmBridgeColorScheme): ComposeColorScheme = impl

}