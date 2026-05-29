package service

import AppScope
import dao.DefaultDataFetcherDao
import me.tatarka.inject.annotations.Inject
import kotlin.random.Random

@AppScope
@Inject
class DefaultRandomNumberService(private val dataFetcherDao: DefaultDataFetcherDao) : RandomNumberService {

    override suspend fun getRandomNumber(): Int {
        println("Get Random Number")
        val nextInt = Random.nextInt(100)
        val id = dataFetcherDao.loadData(nextInt).id
        println("Number is $id")
        return id
    }
}
