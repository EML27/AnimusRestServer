package com.itis.restproject.server.model

import com.itis.restproject.server.repo.GenreRepository
import lombok.NoArgsConstructor
import javax.persistence.*


@NoArgsConstructor
@Entity(name = "genre")
class Genre(var name: String) {
    constructor() : this("")

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val genreId: Int? = null

    @ManyToMany(mappedBy = "genresTable")
    var titles: Set<Title> = HashSet()

    @ManyToMany(mappedBy = "favouriteGenres")
    var users: Set<User> = HashSet()

    companion object {

        fun getGenreByName(name: String, repository: GenreRepository): Genre {

            val result = repository.findGenreByName(name)
            return if (result.isPresent) {
                result.get()
            } else {
                val newGenre = Genre(name)
                repository.save(newGenre)
                newGenre
            }
        }
    }

    override fun toString(): String {
        return name
    }
}
