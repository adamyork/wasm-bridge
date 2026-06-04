package gui.bodyMain

import io.github.oshai.kotlinlogging.KotlinLogging
import kotlinx.browser.document
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.w3c.dom.HTMLButtonElement
import org.w3c.dom.HTMLDivElement
import org.w3c.dom.HTMLLabelElement
import service.RandomNumberService

class ButtonGroupCached {

    private val logger = KotlinLogging.logger {}

    fun build(uiScope: CoroutineScope, randomNumberService: RandomNumberService): HTMLDivElement {
        val cachedRandomContainer = document.createElement("div") as HTMLDivElement
        cachedRandomContainer.className = "random-number-container"

        val cachedRandomNumberButton = document.createElement("button") as HTMLButtonElement
        cachedRandomNumberButton.textContent = "Generate Random Number Cached"
        cachedRandomNumberButton.className = "btn"
        cachedRandomContainer.appendChild(cachedRandomNumberButton)

        val cachedRandomNumberLabel = document.createElement("label") as HTMLLabelElement
        cachedRandomNumberLabel.className = "lbl"
        cachedRandomContainer.appendChild(cachedRandomNumberLabel)

        cachedRandomNumberButton.onclick = {
            uiScope.launch {
                try {
                    val randomNum = randomNumberService.getRandomNumberCached()
                    cachedRandomNumberLabel.textContent = randomNum.toString()
                } catch (t: Throwable) {
                    cachedRandomNumberLabel.textContent = "Error loading number"
                    logger.error { "Failed to generate number: ${t.message}" }
                }
            }

        }
        return cachedRandomContainer
    }
}
