package com.itis.restproject.server.repo

import com.itis.restproject.server.model.Genre
import org.springframework.data.jpa.repository.JpaRepository

import java.io.IOException

interface GenreRepository : JpaRepository<Genre, Int> {

    //Не работает. Кидает result must not be null если в бд нет хотя бы одного из жанров
//    fun findByName(name: String): Genre {
//        for (genre in this.findAll()) {
//            if (genre.name == name) {
//                return genre
//            }
//        }
//        val res = Genre(name)
//        save(res)
//        return res
//    }

    fun findGenreByName(name: String): Genre
}