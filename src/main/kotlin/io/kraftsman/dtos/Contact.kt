package io.kraftsman.dtos

import kotlinx.serialization.Serializable

@Serializable
data class Contact(
    val id: Long?,
    val name: String,
    val email: String,
    val createdAt: String,
    val updatedAt: String
)
