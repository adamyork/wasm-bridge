package gui.bodyMain

import gui.BodyElement
import io.github.oshai.kotlinlogging.KotlinLogging
import kotlinx.browser.document
import kotlinx.coroutines.MainScope
import me.tatarka.inject.annotations.Inject
import org.w3c.dom.*
import service.RandomNumberService

@Inject
class BodyMain(private val randomNumberService: RandomNumberService) : BodyElement {

    private val logger = KotlinLogging.logger {}

    private val uiScope = MainScope()

    override fun build() {
        logger.info { "starting to build body main" }
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

        val buttonGroupNoCache = ButtonGroupNoCache()
        val buttonGroupNoCacheContent = buttonGroupNoCache.build(uiScope, randomNumberService)

        article.appendChild(buttonGroupNoCacheContent)

        val hr2 = document.createElement("hr") as HTMLHRElement
        article.appendChild(hr2)

        val buttonGroupCached = ButtonGroupCached()
        val buttonGroupCachedContent = buttonGroupCached.build(uiScope, randomNumberService)

        article.appendChild(buttonGroupCachedContent)

        val hr3 = document.createElement("hr") as HTMLHRElement
        article.appendChild(hr3)

        val buttonGroupParallel = ButtonGroupParallel()
        val buttonGroupParallelContent = buttonGroupParallel.build(uiScope, randomNumberService)
        article.appendChild(buttonGroupParallelContent)

        main?.appendChild(article)
        logger.info { "finished building body main." }
    }
}