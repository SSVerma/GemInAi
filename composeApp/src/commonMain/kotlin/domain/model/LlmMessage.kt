package domain.model

data class LlmMessage(
    val candidates: List<LlmCandidate>
)

data class LlmCandidate(
    val content: LlmContent,

    // TODO: Revisit -> creates enums
    val finishReason: String? = null
)
