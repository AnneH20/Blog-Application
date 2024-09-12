package com.example.blog

import org.springframework.http.HttpStatus
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.server.ResponseStatusException

@Controller
class HtmlController(
    private val articleRepository: ArticleRepository,
    private val properties: BlogProperties,
    private val userRepository: UserRepository,
) {
    @GetMapping("/home")
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
        println("Rendering article: $article")
        model["title"] = article.title
        model["article"] = article
        return "article"
    }

    @GetMapping("/article/new")
    fun showNewArticleForm(): String {
        return "newArticleForm" // Mustache template for the form
    }

    @PostMapping("/article/new")
    fun submitNewArticleForm(
        @RequestParam title: String,
        @RequestParam headline: String,
        @RequestParam content: String,
        @RequestParam username: String,
    ): String {
        val user: User =
            userRepository.findByUsername(username)
                ?: throw IllegalArgumentException("Author not found")

        val article =
            Article(
                title = title,
                headline = headline,
                content = content,
                author = user,
            )
        articleRepository.save(article)
        return "redirect:/home"
    }

    @GetMapping("/user/new")
    fun showUserForm(): String {
        return "newUserForm" // Mustache template for the form
    }

    @PostMapping("/user/new")
    fun submitNewUserForm(
        @RequestParam username: String,
        @RequestParam firstname: String,
        @RequestParam lastname: String,
    ): String {
        val user =
            User(
                username = username,
                firstName = firstname,
                lastName = lastname,
            )
        userRepository.save(user)
        return "redirect:/home" // Redirect after form submission
    }

    fun Article.render() =
        RenderedArticle(
            slug,
            title,
            headline,
            content,
            RenderedUser(author.firstName, author.lastName),
            addedAt.format(),
        )

    data class RenderedArticle(
        val slug: String,
        val title: String,
        val headline: String,
        val content: String,
        val author: RenderedUser,
        val addedAt: String,
    )

    data class RenderedUser(
        val firstName: String,
        val lastName: String,
    )
}
