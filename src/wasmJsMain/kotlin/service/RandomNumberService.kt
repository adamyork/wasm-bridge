package service

interface RandomNumberService {

    suspend fun getRandomNumber(): Int

}