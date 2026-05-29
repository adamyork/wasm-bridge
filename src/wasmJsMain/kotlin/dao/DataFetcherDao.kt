package dao

import dao.data.Todo

interface DataFetcherDao {

    suspend fun loadData(id: Int): Todo

}