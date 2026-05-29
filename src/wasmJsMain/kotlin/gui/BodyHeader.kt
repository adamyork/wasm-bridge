@file:OptIn(ExperimentalWasmJsInterop::class)

package gui

import external.ExampleLibProxy
import kotlinx.browser.document
import me.tatarka.inject.annotations.Inject
import org.w3c.dom.HTMLAnchorElement

@Inject
class BodyHeader : BodyElement {

    override fun build() {
        println("Kotlin: Starting to build header...")
        val header = document.querySelector("header")
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
        println("Kotlin: header built...")
    }

}

