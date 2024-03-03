package data.mapper

import domain.model.Role

interface RoleMapper {
    fun map(role: Role): String
    fun map(role: String): Role
}

val ollamaRoleMapper by lazy {
    object : RoleMapper {
        val USER = "user"
        val MODEL = "assistant"

        override fun map(role: Role): String {
            return when (role) {
                Role.User -> USER
                Role.Model -> MODEL
            }
        }

        override fun map(role: String): Role {
            return when {
                role == USER -> Role.User
                else -> Role.Model
            }
        }
    }
}
