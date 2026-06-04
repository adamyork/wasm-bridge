package dao

import AppScope
import dao.data.Todo
import io.github.reactivecircus.cache4k.Cache
import kotlinx.browser.window
import kotlinx.coroutines.await
import kotlinx.serialization.json.Json
import me.tatarka.inject.annotations.Inject

@AppScope
@Inject
class DefaultDataFetcherDao(private val dataCache: Cache<Long, Todo>) : DataFetcherDao {

    override suspend fun loadData(id: Int, bustCache: Boolean): Todo {
        return try {
            println("about to fetch todos")
            if (bustCache) {
                fetchTodo(id)
            } else {
                dataCache.get(1) {
                    println("Cached id not preset fetching")
                    fetchTodo(id)
                }
            }
        } catch (t: Throwable) {
            println("Request failed: ${t.message}")
            throw t
        }
    }

    @OptIn(ExperimentalWasmJsInterop::class)
    private suspend fun fetchTodo(id: Int): Todo {
        println("in the suspend")
        val response = window.fetch("https://jsonplaceholder.typicode.com/todos/${id}").await()

        if (!response.ok) {
            println("response not ok")
            error("HTTP ${response.status}: ${response.statusText}")
        }

        val body: JsString = response.text().await()
        return Json.decodeFromString(body.toString())
    }
}
