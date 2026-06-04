package service

import AppScope
import dao.DefaultDataFetcherDao
import io.github.reactivecircus.cache4k.Cache
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import me.tatarka.inject.annotations.Inject
import kotlin.random.Random

@AppScope
@Inject
class DefaultRandomNumberService(
    private val dataFetcherDao: DefaultDataFetcherDao,
    private val dataCache: Cache<Long, Int>
) : RandomNumberService {

    override suspend fun getRandomNumber(): Int {
        println("Get Random Number")
        val nextInt = Random.nextInt(100)
        val id = dataFetcherDao.loadData(nextInt).id
        println("Number is $id")
        return id
    }

    override suspend fun getRandomNumberCached(): Int {
        println("Get cached Number")
        val userId = 1L
        val id = dataCache.get(userId) {
            println("Cached id not preset fetching")
            val nextInt = Random.nextInt(100)
            dataFetcherDao.loadData(nextInt).id
        }
        println("cached number retrieved")
        return id
    }

    override suspend fun getRandomNumbersAndSum(): Int = coroutineScope {
        val first = async { dataFetcherDao.loadData(Random.nextInt(100)).id }
        val second = async { dataFetcherDao.loadData(Random.nextInt(100)).id }
        awaitAll(first, second).sum()
    }
}
