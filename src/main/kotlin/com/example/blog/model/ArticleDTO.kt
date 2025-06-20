package com.example.blog.model

data class ArticleDTO(
    val slug: String,
    val title: String,
    val content: String,
    val description: String,
    val author: UserDTO,
    val addedAt: String,
)
