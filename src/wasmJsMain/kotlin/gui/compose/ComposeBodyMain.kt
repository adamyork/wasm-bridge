package gui.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import coil3.ImageLoader
import coil3.compose.AsyncImage
import coil3.compose.LocalPlatformContext
import coil3.compose.setSingletonImageLoaderFactory
import coil3.network.ktor3.KtorNetworkFetcherFactory
import coil3.request.ImageRequest
import coil3.size.Size
import kotlinx.coroutines.MainScope
import me.tatarka.inject.annotations.Inject
import service.RandomNumberService

@Inject
class ComposeBodyMain(
    private val randomNumberService: RandomNumberService,
    private val composeColorScheme: ComposeColorScheme
) : ComposeBodyElement {

    private val uiScope = MainScope()

    @Composable
    override fun build() {
        val context = LocalPlatformContext.current
        val composeButtonGroupNoCache = ComposeButtonGroupNoCache()
        val composeButtonGroupCached = ComposeButtonGroupCached()
        val composeButtonGroupParallel = ComposeButtonGroupParallel()
        val composeScreenLayer = ComposeScreenLayer()
        setSingletonImageLoaderFactory { context ->
            ImageLoader.Builder(context)
                .components {
                    add(KtorNetworkFetcherFactory())
                }
                .build()
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
                .semantics {
                    contentDescription = "Main content area"
                }
                .testTag("compose-body-main")
                .background(
                    color = MaterialTheme.colorScheme.surfaceContainer,
                    shape = MaterialTheme.shapes.medium
                )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .semantics { contentDescription = "Main content stack" }
                    .testTag("compose-body-main-column")
                    .padding(16.dp), // Typical M3 content block padding
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {

                AsyncImage(
                    model = ImageRequest.Builder(context)
                        .data("https://picsum.photos/800/400")
                        .size(Size.ORIGINAL)
                        .build(),
                    contentDescription = "Abstract modern visual representing data bridge",
                    modifier = Modifier
                        .wrapContentSize() // Adjust height as desired
                        .semantics { contentDescription = "Main hero image" }
                        .testTag("main-hero-image"),
                    contentScale = ContentScale.None
                )

                Text(
                    text = "Some Header",
                    style = MaterialTheme.typography.headlineMedium,
                    color = MaterialTheme.colorScheme.onSurface,
                    modifier = Modifier
                        .semantics { contentDescription = "Main section header" }
                        .testTag("main-section-header")
                )

                Text(
                    text = """
        Lorem ipsum dolor sit amet, consectetur adipiscing elit, 
        sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. 
        Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris 
        nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in 
        reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla 
        pariatur. Excepteur sint occaecat cupidatat non proident, 
        sunt in culpa qui officia deserunt mollit anim id est laborum.
    """.trimIndent(),
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier
                        .semantics { contentDescription = "Main section body text" }
                        .testTag("main-section-body-text")
                )

                HorizontalDivider(
                    modifier = Modifier
                        .padding(top = 8.dp)
                        .semantics { contentDescription = "Divider before cached random number section" }
                        .testTag("divider-before-cached"),
                    color = MaterialTheme.colorScheme.outlineVariant
                )

                composeButtonGroupCached.build(uiScope, randomNumberService, composeColorScheme)

                HorizontalDivider(
                    modifier = Modifier
                        .padding(top = 8.dp)
                        .semantics { contentDescription = "Divider before non-cached random number section" }
                        .testTag("divider-before-no-cache"),
                    color = MaterialTheme.colorScheme.outlineVariant
                )

                composeButtonGroupNoCache.build(uiScope, randomNumberService, composeColorScheme)

                HorizontalDivider(
                    modifier = Modifier
                        .padding(top = 8.dp)
                        .semantics { contentDescription = "Divider before parallel random number section" }
                        .testTag("divider-before-parallel"),
                    color = MaterialTheme.colorScheme.outlineVariant
                )

                composeButtonGroupParallel.build(uiScope, randomNumberService, composeColorScheme)

                HorizontalDivider(
                    modifier = Modifier
                        .padding(top = 8.dp)
                        .semantics { contentDescription = "Divider before screen layer section" }
                        .testTag("divider-before-screen-layer"),
                    color = MaterialTheme.colorScheme.outlineVariant
                )

                composeScreenLayer.build()

            }
        }
    }
}
