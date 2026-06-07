package gui.sr

import io.github.oshai.kotlinlogging.KotlinLogging
import kotlinx.browser.document
import me.tatarka.inject.annotations.Inject

@Inject
class BodyFooter : BodyElement {

    private val logger = KotlinLogging.logger {}

    override fun build() {
        logger.info { "starting to build footer" }
        val footer = document.querySelector("#srFooter")
        footer?.setAttribute("role", "contentinfo")
        val p = document.createElement("p")
        p.textContent = "© 2024 Wasm Bridge. All rights reserved."
        footer?.appendChild(p)
        logger.info { "footer built" }
    }

}
