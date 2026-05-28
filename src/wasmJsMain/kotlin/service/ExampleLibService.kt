package service

@OptIn(ExperimentalWasmJsInterop::class)
@JsFun(
    """
() => {
  const fn = document.exampleLib.testFunc
  if (typeof fn === 'function') fn();
}
"""
)
private external fun exampleLintestFunc()

class ExampleLibService {
    fun testFunc() {
        exampleLintestFunc()
    }
}

