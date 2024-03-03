package domain.model

data class LlmGenerationArgs(
    val contents: List<LlmContent>,
    val stream: Boolean = false,
    val generationConfig: GenerationConfig? = null
)

data class GenerationConfig(
    val stopSequences: List<String>,
    val temperature: Float,
    val topP: Float,
    val topK: Float
)