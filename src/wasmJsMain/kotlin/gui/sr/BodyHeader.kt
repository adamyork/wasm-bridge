@file:OptIn(ExperimentalWasmJsInterop::class)

package gui.sr

import external.ExampleLibProxy
import io.github.oshai.kotlinlogging.KotlinLogging
import kotlinx.browser.document
import me.tatarka.inject.annotations.Inject
import org.w3c.dom.HTMLAnchorElement

@Inject
class BodyHeader : BodyElement {

    private val logger = KotlinLogging.logger {}

    override fun build() {
        logger.info { "starting to build header" }
        val header = document.querySelector("#srHead")
        val logoLink = document.createElement("a") as HTMLAnchorElement
        logoLink.href = "#"
        logoLink.className = "logo"
        logoLink.textContent = "Wasm Bridge"
        header?.appendChild(logoLink)

        val exampleLibProxy = ExampleLibProxy()
        exampleLibProxy.invokeTestFunc()

        val nav = document.createElement("nav")
        val ul = document.createElement("ul")

        val menuItems = listOf(
            "One" to "#one",
            "Two" to "#two",
            "Three" to "#three"
        )

        for ((text, hash) in menuItems) {
            val li = document.createElement("li")
            val a = document.createElement("a") as HTMLAnchorElement

            a.href = hash
            a.textContent = text

            li.appendChild(a)
            ul.appendChild(li)
        }
        nav.appendChild(ul)
        header?.appendChild(nav)
        logger.info { "header built" }
    }

}

