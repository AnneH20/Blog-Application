package com.example.blog

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import java.time.LocalDateTime

@Entity
data class User(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long? = null,
    var username: String,
    @Column(name = "first_name")
    var firstName: String,
    @Column(name = "last_name")
    var lastName: String,
)

@Entity
data class Article(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long? = null,
    var title: String,
    var headline: String,
    var content: String,
    @ManyToOne
    @JoinColumn(name = "author_id")
    var author: User,
    var slug: String = title.toSlug(),
    @Column(name = "added_at")
    var addedAt: LocalDateTime = LocalDateTime.now(),
)
