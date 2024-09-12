package com.example.blog

import org.springframework.http.HttpStatus
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.server.ResponseStatusException

@Controller
class HtmlController(
    private val articleRepository: ArticleRepository,
    private val properties: BlogProperties,
    private val userRepository: UserRepository,
) {
    @GetMapping("/")
    fun blog(model: Model): String {
        model["title"] = properties.title
        model["banner"] = properties.banner
        model["articles"] = articleRepository.findAllByOrderByAddedAtDesc().map { it.render() }
        return "blog"
    }

    @GetMapping("/article/{slug}")
    fun article(
        @PathVariable slug: String,
        model: Model,
    ): String {
        val article =
            articleRepository
                .findBySlug(slug)
                ?.render()
                ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "This article does not exist")
        model["title"] = article.title
        model["article"] = article
        return "article"
    }

    @GetMapping("/article/new")
    fun showForm(): String {
        return "newArticleForm" // Mustache template for the form
    }

    @PostMapping("/article/new")
    @ResponseStatus(HttpStatus.CREATED)
    fun submitForm(
        @RequestParam title: String,
        @RequestParam headline: String,
        @RequestParam content: String,
        @RequestParam author: String,
    ): String {
        val user: User =
            userRepository.findByLogin(author)
                ?: throw IllegalArgumentException("Author not found")

        val article =
            Article(
                title = title,
                headline = headline,
                content = content,
                author = user,
            )
        articleRepository.save(article)
        return "redirect:/" // Redirect after form submission
    }

    fun Article.render() =
        RenderedArticle(
            slug,
            title,
            headline,
            content,
            author,
            addedAt.format(),
        )

    data class RenderedArticle(
        val slug: String,
        val title: String,
        val headline: String,
        val content: String,
        val author: User,
        val addedAt: String,
    )
}
