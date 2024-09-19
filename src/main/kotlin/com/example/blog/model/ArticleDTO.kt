package com.example.blog.model

data class RenderedArticleDTO(
    val slug: String,
    val title: String,
    val content: String,
    val description: String,
    val author: RenderedUserDTO,
    val addedAt: String,
)
