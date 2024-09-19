package com.example.blog.repository

import com.example.blog.model.UserDo
import org.springframework.data.repository.CrudRepository

interface UserRepository : CrudRepository<UserDo, Long> {
    fun findByUsername(username: String): UserDo?
}
