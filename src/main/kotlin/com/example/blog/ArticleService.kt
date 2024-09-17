package com.example.blog

import org.springframework.stereotype.Service

@Service
class ArticleService(
    private val articleRepository: ArticleRepository,
) {
    fun searchArticles(query: String): List<Article> {
        val titleMatches = articleRepository.findByTitleContainingIgnoreCase(query)
        val contentMatches = articleRepository.findByContentContainingIgnoreCase(query)
        return (titleMatches + contentMatches).distinct()
    }
}
