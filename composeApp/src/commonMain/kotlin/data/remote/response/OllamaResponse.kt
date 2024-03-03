package data.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class OllamaPromptResponse(
    @SerialName("model")
    val model: String,

    @SerialName("created_at")
    val createdAt: String,

    @SerialName("response")
    val message: String,

    @SerialName("images")
    val images: List<String>?,

    @SerialName("done")
    val done: Boolean
)

@Serializable
class OllamaChatResponse(
    @SerialName("model")
    val model: String,

    @SerialName("created_at")
    val createdAt: String,

    @SerialName("message")
    val message: OllamaChatMessage,

    @SerialName("done")
    val done: Boolean
)

@Serializable
class OllamaChatMessage(
    @SerialName("role")
    val role: String,

    @SerialName("content")
    val text: String,

    @SerialName("images")
    val images: List<String>?
)

@Serializable
data object OllamaErrorBody
