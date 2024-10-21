package com.example.blog.controller

import com.example.blog.BlogProperties
import com.example.blog.render
import com.example.blog.service.ArticleService
import com.example.blog.service.UserService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.servlet.mvc.support.RedirectAttributes

@Controller
class HtmlController(
    private val articleService: ArticleService,
    private val userService: UserService,
    private val properties: BlogProperties,
) {
    @GetMapping("/", "/home")
    fun blog(model: Model): String {
        model["title"] = properties.title
        model["banner"] = properties.banner
        model["articles"] = articleService.findAllArticles().map { it.render() }
        return "blog"
    }

    @GetMapping("/articles")
    fun getArticles(model: Model): String {
        model["banner"] = properties.banner
        model["articles"] = articleService.findAllArticles().map { it.render() }
        return "articles"
    }

    @GetMapping("/users")
    fun getUsers(model: Model): String {
        model["banner"] = properties.banner
        model["users"] = userService.findAllUsers()
        return "users"
    }

    @GetMapping("/article/{slug}")
    fun article(
        @PathVariable slug: String,
        model: Model,
    ): String {
        val article = articleService.findArticleBySlug(slug).render()
        model["banner"] = properties.banner
        model["title"] = article.title
        model["article"] = article
        return "article"
    }

    @GetMapping("/article/new")
    fun showNewArticleForm(): String = "newArticleForm"

    @PostMapping("/article/new")
    fun submitNewArticleForm(
        @RequestParam title: String,
        @RequestParam content: String,
        @RequestParam username: String,
    ): String {
        articleService.createArticle(title, content, username)
        return "redirect:/articles"
    }

    @GetMapping("/user/new")
    fun showUserForm(): String = "newUserForm"

    @PostMapping("/user/new")
    fun submitNewUserForm(
        @RequestParam username: String,
        @RequestParam firstname: String,
        @RequestParam lastname: String,
    ): String {
        userService.createUser(username, firstname, lastname)
        return "redirect:/users"
    }

    @GetMapping("/user/delete")
    fun deleteUserForm(): String = "deleteUserForm"

    @PostMapping("/user/delete")
    fun deleteUser(
        @RequestParam username: String,
        redirectAttributes: RedirectAttributes,
    ): String =
        try {
            userService.deleteUserByUsername(username)
            "redirect:/users"
        } catch (e: IllegalArgumentException) {
            redirectAttributes.addAttribute("error", e.message)
            "redirect:/user/delete"
        }

    @GetMapping("/user/{username}/edit")
    fun showEditUserForm(
        @PathVariable username: String,
        model: Model,
    ): String {
        val user = userService.findUserByUsername(username)
        model.addAttribute("user", user)
        return "editUserForm"
    }

    @PostMapping("/user/{originalUsername}/edit")
    fun editUser(
        @PathVariable originalUsername: String,
        @RequestParam updatedUsername: String,
        @RequestParam firstName: String,
        @RequestParam lastName: String,
    ): String {
        userService.updateUser(originalUsername, updatedUsername, firstName, lastName)
        return "redirect:/users"
    }

    @GetMapping("/article/delete")
    fun deleteArticleForm(): String = "deleteArticleForm"

    @PostMapping("/article/delete")
    fun deleteArticle(
        @RequestParam title: String,
        redirectAttributes: RedirectAttributes,
    ): String =
        try {
            articleService.deleteArticleByTitle(title)
            "redirect:/articles"
        } catch (e: IllegalArgumentException) {
            redirectAttributes.addAttribute("error", e.message)
            "redirect:/article/delete"
        }

    @GetMapping("/article/{slug}/edit")
    fun showEditArticleForm(
        @PathVariable slug: String,
        model: Model,
    ): String {
        val article = articleService.findArticleBySlug(slug)
        model.addAttribute("article", article)
        return "editArticleForm"
    }

    @PostMapping("/article/{slug}/edit")
    fun editArticle(
        @PathVariable slug: String,
        @RequestParam title: String,
        @RequestParam content: String,
    ): String {
        articleService.updateArticle(slug, title, content)
        return "redirect:/article/$slug"
    }
}
