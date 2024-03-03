package data.mapper

import data.remote.request.OllamaChatMessage
import data.remote.request.OllamaChatBody
import data.remote.request.LlmModel
import data.remote.request.OllamaPromptBody
import domain.model.Base64
import domain.model.Blob
import domain.model.LlmGenerationArgs
import domain.model.Part

private fun Part?.asImages(): List<Base64>? {
    return this?.blobs?.map { blob ->
        when (blob) {
            is Blob.Image -> blob.rawData
        }
    }
}

fun LlmGenerationArgs.asOllamaPromptBody(model: LlmModel): OllamaPromptBody {
    val content = this.contents.lastOrNull()

    return OllamaPromptBody(
        model = model.completeName(),
        stream = false,
        prompt = content?.part?.text.orEmpty(),
        images = content?.part?.asImages()
    )
}

fun LlmGenerationArgs.asOllamaChatBody(
    model: LlmModel,
    roleMapper: RoleMapper = ollamaRoleMapper
): OllamaChatBody {

    val chatMessages = this.contents.map { content ->
        OllamaChatMessage(
            role = roleMapper.map(content.role),
            text = content.part.text,
            images = content.part.asImages()
        )
    }

    return OllamaChatBody(
        model = model.completeName(),
        stream = false,
        messages = chatMessages
    )
}
