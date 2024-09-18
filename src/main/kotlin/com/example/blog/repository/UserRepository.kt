package com.example.blog.repository

import com.example.blog.model.UserDTO.User
import org.springframework.data.repository.CrudRepository

interface UserRepository : CrudRepository<User, Long> {
    fun findByUsername(username: String): User?
}
