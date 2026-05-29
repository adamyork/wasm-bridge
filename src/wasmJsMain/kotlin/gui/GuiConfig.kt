package gui

import AppScope
import Footer
import Header
import Main
import me.tatarka.inject.annotations.Provides

interface GuiConfig {

    @Header
    val bodyHeader: BodyElement

    @Main
    val bodyMain: BodyElement

    @Footer
    val bodyFooter: BodyElement

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

}