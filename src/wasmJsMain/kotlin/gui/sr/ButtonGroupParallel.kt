package gui.sr

import io.github.oshai.kotlinlogging.KotlinLogging
import kotlinx.browser.document
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.w3c.dom.HTMLButtonElement
import org.w3c.dom.HTMLDivElement
import org.w3c.dom.HTMLLabelElement
import service.RandomNumberService

class ButtonGroupParallel {

    private val logger = KotlinLogging.logger {}

    fun build(uiScope: CoroutineScope, randomNumberService: RandomNumberService): HTMLDivElement {
        val parallelRandomSumContainer = document.createElement("div") as HTMLDivElement
        parallelRandomSumContainer.className = "random-number-container"

        val parallelRandomSumButton = document.createElement("button") as HTMLButtonElement
        parallelRandomSumButton.textContent = "Generate Random Number From Two"
        parallelRandomSumButton.className = "btn"
        parallelRandomSumContainer.appendChild(parallelRandomSumButton)

        val parallelRandomSumLabel = document.createElement("label") as HTMLLabelElement
        parallelRandomSumLabel.className = "lbl"
        parallelRandomSumContainer.appendChild(parallelRandomSumLabel)

        parallelRandomSumButton.onclick = {
            uiScope.launch {
                try {
                    val randomNum = randomNumberService.getRandomNumbersAndSum()
                    parallelRandomSumLabel.textContent = randomNum.toString()
                } catch (t: Throwable) {
                    parallelRandomSumLabel.textContent = "Error loading number"
                    logger.error { "Failed to generate number: ${t.message}" }
                }
            }
        }
        return parallelRandomSumContainer
    }

}