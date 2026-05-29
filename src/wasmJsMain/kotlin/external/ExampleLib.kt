package external

@OptIn(ExperimentalWasmJsInterop::class)
external interface ExampleLib: JsAny {
    val testFunc: (() -> Unit)?
}
