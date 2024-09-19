package com.example.blog.repository

import com.example.blog.model.UserDo
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : CrudRepository<UserDo, Long> {
    fun findByUsername(username: String): UserDo?
}
