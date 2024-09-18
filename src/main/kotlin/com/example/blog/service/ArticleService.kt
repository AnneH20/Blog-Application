package com.example.blog.service

import com.example.blog.model.Article
import com.example.blog.repository.ArticleRepository
import com.example.blog.repository.UserRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class ArticleService(
    private val articleRepository: ArticleRepository,
    private val userRepository: UserRepository,
) {
    fun findAllArticles(): Iterable<Article> = articleRepository.findAllByOrderByAddedAtDesc()

    fun findArticleBySlug(slug: String): Article =
        articleRepository.findBySlug(slug)
            ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "This article does not exist")

    fun createArticle(
        title: String,
        content: String,
        username: String,
    ): Article {
        val author =
            userRepository.findByUsername(username)
                ?: throw IllegalArgumentException("Author not found")

        val article =
            Article(
                title = title,
                content = content,
                author = author,
            )
        return articleRepository.save(article)
    }

    fun deleteArticleByTitle(title: String) {
        val article =
            articleRepository.findByTitleIgnoreCase(title)
                ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "This article does not exist")
        articleRepository.delete(article)
    }
}
