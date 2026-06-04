package service

import AppScope
import dao.DefaultDataFetcherDao
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import me.tatarka.inject.annotations.Inject
import kotlin.random.Random

@AppScope
@Inject
class DefaultRandomNumberService(
    private val dataFetcherDao: DefaultDataFetcherDao
) : RandomNumberService {

    override suspend fun getRandomNumber(): Int {
        println("Get Random Number")
        val nextInt = Random.nextInt(100)
        val id = dataFetcherDao.loadData(nextInt, true).id
        println("Number is $id")
        return id
    }

    override suspend fun getRandomNumberCached(): Int {
        println("Get cached Number")
        val nextInt = Random.nextInt(100)
        return dataFetcherDao.loadData(nextInt, false).id
    }

    override suspend fun getRandomNumbersAndSum(): Int = coroutineScope {
        val first = async { dataFetcherDao.loadData(Random.nextInt(100), true).id }
        val second = async { dataFetcherDao.loadData(Random.nextInt(100), true).id }
        awaitAll(first, second).sum()
    }
}
