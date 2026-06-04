package service

import AppScope
import io.github.reactivecircus.cache4k.Cache
import me.tatarka.inject.annotations.Provides
import kotlin.time.Duration.Companion.seconds

interface ServiceConfig {

    val randomNumberService: RandomNumberService

    @AppScope
    @Provides
    fun provideRandomNumberService(impl: DefaultRandomNumberService): RandomNumberService = impl

    @AppScope
    @Provides
    fun providesDataCache(): Cache<Long, Int> {
        return Cache.Builder<Long, Int>()
            .expireAfterAccess(5.seconds)
            .build()
    }

}
