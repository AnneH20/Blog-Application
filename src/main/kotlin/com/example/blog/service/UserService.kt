package com.example.blog.service

import com.example.blog.model.UserDo
import com.example.blog.repository.ArticleRepository
import com.example.blog.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository,
    private val articleRepository: ArticleRepository,
) {
    fun findAllUsers(): Iterable<UserDo> = userRepository.findAll()

    fun createUser(
        username: String,
        firstName: String,
        lastName: String,
    ): UserDo {
        val user =
            UserDo(
                username = username,
                firstName = firstName,
                lastName = lastName,
            )
        return userRepository.save(user)
    }

    fun updateUser(
        originalUsername: String,
        updatedUsername: String,
        firstName: String,
        lastName: String,
    ): UserDo {
        val user =
            userRepository.findByUsername(originalUsername)
                ?: throw IllegalArgumentException("User not found")
        user.username = updatedUsername
        user.firstName = firstName
        user.lastName = lastName
        val savedUser = userRepository.save(user)

        if (originalUsername != updatedUsername) {
            val articles = articleRepository.findByAuthorUsername(originalUsername)
            articles.forEach {
                it.author.username = updatedUsername
                articleRepository.save(it)
            }
        }
        return savedUser
    }

    fun findUserByUsername(username: String): UserDo =
        userRepository.findByUsername(username)
            ?: throw IllegalArgumentException("User not found")

    fun deleteUserByUsername(username: String) {
        val user =
            userRepository.findByUsername(username)
                ?: throw IllegalArgumentException("User not found")
        userRepository.delete(user)
    }
}
