package data.mapper

import data.remote.response.OllamaChatResponse
import data.remote.response.OllamaPromptResponse
import domain.model.Base64
import domain.model.Blob
import domain.model.LlmCandidate
import domain.model.LlmContent
import domain.model.LlmMessage
import domain.model.Part
import domain.model.Role

private fun List<Base64>?.asDomainImages(): List<Blob.Image>? {
    return this?.map { rawData ->
        Blob.Image(rawData = rawData)
    }
}

fun OllamaPromptResponse.asLlmMessage(): LlmMessage {
    return LlmMessage(
        candidates = listOf(
            LlmCandidate(
                content = LlmContent(
                    role = Role.Model,
                    part = Part(
                        text = message,
                        blobs = images.asDomainImages()
                    )
                )
            )
        )
    )
}

fun OllamaChatResponse.asLlmMessage(roleMapper: RoleMapper = ollamaRoleMapper): LlmMessage {
    return LlmMessage(
        candidates = listOf(
            LlmCandidate(
                content = LlmContent(
                    role = roleMapper.map(this.message.role),
                    part = Part(
                        text = this.message.text,
                        blobs = this.message.images.asDomainImages()
                    )
                )
            )
        )
    )
}
