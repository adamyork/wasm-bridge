package service

interface RandomNumberService {

    suspend fun getRandomNumber(): Int

    suspend fun getRandomNumberCached(): Int

    suspend fun getRandomNumbersAndSum(): Int

}
