package external

@OptIn(ExperimentalWasmJsInterop::class)
private val documentExampleLib: ExampleLib = js("document.exampleLib")

class ExampleLibProxy {

    private fun getExampleLib(): ExampleLib = documentExampleLib

    fun invokeTestFunc() {
        getExampleLib().testFunc?.invoke()
    }
}