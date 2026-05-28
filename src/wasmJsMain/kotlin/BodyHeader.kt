@file:OptIn(ExperimentalWasmJsInterop::class)

import kotlinx.browser.document
import org.w3c.dom.HTMLAnchorElement
import service.ExampleLibService

class BodyHeader {

    fun buildUI() {
        println("Kotlin: Starting to build header...")
        val header = document.querySelector("header")
        val logoLink = document.createElement("a") as HTMLAnchorElement
        logoLink.href = "#"
        logoLink.className = "logo"
        logoLink.textContent = "Wasm Bridge"
        header?.appendChild(logoLink)

        val exampleLibService = ExampleLibService()
        exampleLibService.testFunc()

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

