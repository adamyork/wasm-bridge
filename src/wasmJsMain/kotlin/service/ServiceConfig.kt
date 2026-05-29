package service

import AppScope
import me.tatarka.inject.annotations.Provides

interface ServiceConfig {

    val randomNumberService: RandomNumberService

    @AppScope
    @Provides
    fun provideRandomNumberService(impl: DefaultRandomNumberService): RandomNumberService = impl

}