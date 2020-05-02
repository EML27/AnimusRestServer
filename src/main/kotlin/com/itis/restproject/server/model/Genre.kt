package com.itis.restproject.server.model

import com.itis.restproject.server.repo.GenreRepository
import lombok.NoArgsConstructor
import org.springframework.beans.factory.annotation.Autowired
import java.io.IOException
import java.sql.SQLException
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id


@NoArgsConstructor
@Entity(name = "genre")
class Genre(var name: String) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val genreId: Int? = null

    constructor() : this("")


    companion object {

        fun getGenreByName(name: String, repository: GenreRepository): Genre {

            return Genre(name).also {
                try {
                    repository.save(it)
                } catch (e: Exception) {
                }
            }

        }
    }
}