package data.remote.request

data class OllamaLama2Model(
    override val name: String = "llama2",
    override val tag: String? = null
) : LlmModel
