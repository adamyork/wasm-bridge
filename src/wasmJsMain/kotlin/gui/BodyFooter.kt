package gui

import kotlinx.browser.document
import me.tatarka.inject.annotations.Inject

@Inject
class BodyFooter {

    fun buildUI() {
        println("Kotlin: Starting to build footer...")
        val footer = document.querySelector("footer")
        val p = document.createElement("p")
        p.textContent = "© 2024 Wasm Bridge. All rights reserved."
        footer?.appendChild(p)
        println("Kotlin: footer built")
    }

}
