package com.example.blog.service

import com.example.blog.model.UserDTO.User
import com.example.blog.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository,
) {
    fun findAllUsers(): Iterable<User> = userRepository.findAll()

    fun createUser(
        username: String,
        firstName: String,
        lastName: String,
    ): User {
        val user =
            User(
                username = username,
                firstName = firstName,
                lastName = lastName,
            )
        return userRepository.save(user)
    }

    fun deleteUserByUsername(username: String) {
        val user =
            userRepository.findByUsername(username)
                ?: throw IllegalArgumentException("User not found")
        userRepository.delete(user)
    }
}
