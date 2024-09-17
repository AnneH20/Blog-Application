package com.example.blog

import org.springframework.data.repository.CrudRepository

interface ArticleRepository : CrudRepository<Article, Long> {
    fun findBySlug(slug: String): Article?

    fun findByTitleIgnoreCase(title: String): Article?

    fun findAllByOrderByAddedAtDesc(): Iterable<Article>

    fun findByTitleContainingIgnoreCase(query: String): List<Article>

    fun findByContentContainingIgnoreCase(query: String): List<Article>
}

interface UserRepository : CrudRepository<User, Long> {
    fun findByUsername(username: String): User?

    fun findByUsernameContainingIgnoreCase(query: String): List<User>
}
