package service

import dao.DataFetcherDao
import kotlin.random.Random

class RandomNumberService {

    val dataFetcherDao = DataFetcherDao()

    suspend fun getRandomNumber(): Int {
        println("Get Random Number")
        val nextInt = Random.nextInt(100)
        val id = dataFetcherDao.loadData(nextInt).id
        println("Number is $id")
        return id
    }
}
