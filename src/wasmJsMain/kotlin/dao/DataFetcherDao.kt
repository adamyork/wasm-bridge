package dao

import AppScope
import kotlinx.browser.window
import kotlinx.coroutines.await
import kotlinx.serialization.json.Json
import me.tatarka.inject.annotations.Inject

@AppScope
@Inject
class DataFetcherDao {

    suspend fun loadData(id: Int): Todo {
        return try {
            println("about to fetch todos")
            fetchTodo(id)
        } catch (t: Throwable) {
            println("Request failed: ${t.message}")
            throw t
        }
    }

    @OptIn(ExperimentalWasmJsInterop::class)
    suspend fun fetchTodo(id: Int): Todo {
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
