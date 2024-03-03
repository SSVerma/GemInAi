package data.remote.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class OllamaPromptBody(
    @SerialName("model")
    val model: String,

    @SerialName("stream")
    val stream: Boolean,

    @SerialName("prompt")
    val prompt: String,

    @SerialName("images")
    val images: List<String>?
)

@Serializable
class OllamaChatBody(
    @SerialName("model")
    val model: String,

    @SerialName("stream")
    val stream: Boolean,

    @SerialName("messages")
    val messages: List<OllamaChatMessage>
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