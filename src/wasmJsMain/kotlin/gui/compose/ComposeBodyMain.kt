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
    override fun build(): Unit {
        val context = LocalPlatformContext.current
        val composeButtonGroupNoCache = ComposeButtonGroupNoCache()
        val composeButtonGroupCached = ComposeButtonGroupCached()
        val composeButtonGroupParallel = ComposeButtonGroupParallel()
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
                .background(
                    color = MaterialTheme.colorScheme.surfaceContainer,
                    shape = MaterialTheme.shapes.medium
                )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp), // Typical M3 content block padding
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {

                AsyncImage(
                    model = ImageRequest.Builder(context)
                        .data("https://picsum.photos/800/400")
                        .size(Size.ORIGINAL)
                        .build(),
                    contentDescription = "Abstract modern visual representing data bridge",
                    modifier = Modifier.wrapContentSize(), // Adjust height as desired
                    contentScale = ContentScale.None
                )

                Text(
                    text = "Some Header",
                    style = MaterialTheme.typography.headlineMedium,
                    color = MaterialTheme.colorScheme.onSurface
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
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

                HorizontalDivider(
                    modifier = Modifier.padding(top = 8.dp),
                    color = MaterialTheme.colorScheme.outlineVariant
                )

                composeButtonGroupCached.build(uiScope, randomNumberService, composeColorScheme)

                HorizontalDivider(
                    modifier = Modifier.padding(top = 8.dp),
                    color = MaterialTheme.colorScheme.outlineVariant
                )

                composeButtonGroupNoCache.build(uiScope, randomNumberService, composeColorScheme)

                HorizontalDivider(
                    modifier = Modifier.padding(top = 8.dp),
                    color = MaterialTheme.colorScheme.outlineVariant
                )

                composeButtonGroupParallel.build(uiScope, randomNumberService, composeColorScheme)

            }
        }
    }
}