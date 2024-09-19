package com.example.blog.model

import jakarta.persistence.CascadeType
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.OneToMany
import jakarta.persistence.Table

@Entity
@Table(name = "users")
data class UserDo(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id: Long? = null,
    var username: String,
    @Column(name = "first_name")
    var firstName: String,
    @Column(name = "last_name")
    var lastName: String,
    @OneToMany(mappedBy = "author", cascade = [CascadeType.REMOVE], orphanRemoval = true)
    val articles: List<ArticleDo> = mutableListOf(),
)
