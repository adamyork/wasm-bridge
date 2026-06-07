package gui.sr

import io.github.oshai.kotlinlogging.KotlinLogging
import kotlinx.browser.document
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.w3c.dom.HTMLButtonElement
import org.w3c.dom.HTMLDivElement
import org.w3c.dom.HTMLLabelElement
import service.RandomNumberService

class ButtonGroupNoCache {

    private val logger = KotlinLogging.logger {}

    fun build(uiScope: CoroutineScope, randomNumberService: RandomNumberService): HTMLDivElement {
        val randomContainer = document.createElement("div") as HTMLDivElement
        randomContainer.className = "random-number-container"

        val randomNumberButton = document.createElement("button") as HTMLButtonElement
        randomNumberButton.textContent = "Generate Random Number"
        randomNumberButton.className = "btn"
        randomNumberButton.setAttribute("aria-controls", "cached-random-label-2")
        randomContainer.appendChild(randomNumberButton)

        val randomNumberLabel = document.createElement("label") as HTMLLabelElement
        randomNumberLabel.className = "lbl"
        randomNumberLabel.setAttribute("role", "status")
        randomNumberLabel.setAttribute("aria-live", "polite")
        randomContainer.appendChild(randomNumberLabel)

        randomNumberButton.onclick = {
            uiScope.launch {
                try {
                    val randomNum = randomNumberService.getRandomNumber()
                    randomNumberLabel.textContent = randomNum.toString()
                } catch (t: Throwable) {
                    randomNumberLabel.textContent = "Error loading number"
                    logger.error { "Failed to generate number: ${t.message}" }
                }
            }
        }
        return randomContainer
    }
}