package domain.model

data class LlmContent(
    val role: Role,
    val part: Part
)

enum class Role {
    User, Model
}

data class Part(
    val text: String,
    val blobs: List<Blob>? = null
)

sealed interface Blob {
    val rawData: Base64
    val mimeType: MimeType

    class Image(
        override val rawData: String,
        override val mimeType: MimeType.Image = MimeType.Image.Unknown
    ) : Blob
}

sealed interface MimeType {
    sealed interface Image : MimeType {
        data object Png : Image
        data object Jpeg : Image
        data object Webp : Image
        data object Unknown : Image
    }
}

typealias Base64 = String
