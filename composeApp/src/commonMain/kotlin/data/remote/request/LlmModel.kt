package data.remote.request

interface LlmModel {
    val name: String
    val tag: String?
    fun completeName(): String {
        return tag?.let { "$name:$it" } ?: name
    }
}