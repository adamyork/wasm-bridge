@file:OptIn(ExperimentalWasmJsInterop::class)

package gui

import kotlinx.browser.document
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import me.tatarka.inject.annotations.Inject
import org.w3c.dom.*
import service.RandomNumberService

@Inject
class BodyMain(private val randomNumberService: RandomNumberService) {

    private val uiScope = MainScope()

    fun buildUI() {
        println("Kotlin: Starting to build body main...")
        val main = document.querySelector("main")
        val img = document.createElement("img") as HTMLImageElement
        img.className = "hero-image"
        img.src = "https://picsum.photos/800/400"
        img.alt = "Abstract modern visual representing data bridge"
        main?.appendChild(img)

        val article = document.createElement("div") as HTMLDivElement
        article.className = "content-block"

        val h2 = document.createElement("h2") as HTMLHeadingElement
        h2.textContent = "Some Header"
        article.appendChild(h2)

        val p = document.createElement("p") as HTMLParagraphElement
        p.textContent = """Lorem ipsum dolor sit amet, consectetur adipiscing elit, 
            sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. 
            Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris 
            nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in 
            reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla 
            pariatur. Excepteur sint occaecat cupidatat non proident, 
            sunt in culpa qui officia deserunt mollit anim id est laborum.""".trimMargin()
        article.appendChild(p)

        val hr = document.createElement("hr") as HTMLHRElement
        article.appendChild(hr)

        val randomContainer = document.createElement("div") as HTMLDivElement
        randomContainer.className = "random-number-container"
        article.appendChild(randomContainer)

        val randomNumberButton = document.createElement("button") as HTMLButtonElement
        randomNumberButton.textContent = "Generate Random Number"
        randomNumberButton.className = "btn"
        randomContainer.appendChild(randomNumberButton)

        val randomNumberLabel = document.createElement("label") as HTMLLabelElement
        randomNumberLabel.className = "lbl"
        randomContainer.appendChild(randomNumberLabel)

        randomNumberButton.onclick = {
            uiScope.launch {
                try {
                    val randomNum = randomNumberService.getRandomNumber()
                    randomNumberLabel.textContent = randomNum.toString()
                } catch (t: Throwable) {
                    randomNumberLabel.textContent = "Error loading number"
                    println("Failed to generate number: ${t.message}")
                }
            }
        }

        main?.appendChild(article)
        println("Kotlin: Finished building body main.")
    }
}
