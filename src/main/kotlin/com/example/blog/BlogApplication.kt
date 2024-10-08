package com.example.blog

import org.springframework.boot.Banner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@EnableConfigurationProperties(BlogProperties::class)
@SpringBootApplication
class BlogApplication

fun main(args: Array<String>) {
    runApplication<BlogApplication>(*args) {
        setBannerMode(Banner.Mode.OFF)
    }
}
