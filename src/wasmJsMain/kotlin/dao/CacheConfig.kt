package dao

import AppScope
import dao.data.Todo
import io.github.reactivecircus.cache4k.Cache
import me.tatarka.inject.annotations.Provides
import kotlin.time.Duration.Companion.seconds

interface CacheConfig {

    val dataCache: Cache<Long, Todo>

    @AppScope
    @Provides
    fun providesDataCache(): Cache<Long, Todo> {
        return Cache.Builder<Long, Todo>()
            .expireAfterAccess(5.seconds)
            .build()
    }
}