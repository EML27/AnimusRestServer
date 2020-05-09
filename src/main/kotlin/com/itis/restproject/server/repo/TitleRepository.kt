package com.itis.restproject.server.repo

import com.itis.restproject.server.model.Title
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*


interface TitleRepository : JpaRepository<Title, Int> {
    fun findTitleByKinopoiskId(kinopoiskId: Int): Optional<Title>

    fun findByCurrentlyPopularIsTrue(): List<Title>

    fun findTitlesByNameContaining(name: String): List<Title>
}