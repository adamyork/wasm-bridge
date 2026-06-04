import io.github.oshai.kotlinlogging.Formatter
import io.github.oshai.kotlinlogging.KLoggingEvent
import io.github.oshai.kotlinlogging.KotlinLoggingConfiguration
import io.github.oshai.kotlinlogging.Level

object LogConfig {

    fun initialize(minimumLevel: Level = Level.INFO) {
        KotlinLoggingConfiguration.direct.logLevel = minimumLevel
        KotlinLoggingConfiguration.direct.formatter = object : Formatter {
            override fun formatMessage(loggingEvent: KLoggingEvent): String {
                val levelStr = loggingEvent.level.name
                val cleanName = loggingEvent.loggerName.substringAfterLast('.')
                return "[wasm-bridge] [kotlin] [${levelStr.lowercase()}] [${cleanName.lowercase()}] -> ${loggingEvent.message?.lowercase()}"
            }
        }
    }

}