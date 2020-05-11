package com.itis.restproject.server.model

import com.itis.restproject.server.dto.response.TitleResponse
import com.itis.restproject.server.repo.GenreRepository
import lombok.Builder
import lombok.NoArgsConstructor
import javax.persistence.*

@Entity(name = "title")
@Builder
@NoArgsConstructor
class Title(val name: String="",
            val kinopoiskId: Int=0,
            val description: String="",
            val posterSource: String="",

            @ManyToMany(cascade = [CascadeType.ALL])
            @JoinTable(name = "title_genre",
                    joinColumns = [JoinColumn(name = "title_id")],
                    inverseJoinColumns = [JoinColumn(name = "genre_id")])
            val genresTable: Set<Genre> = HashSet(),
            val playerSource: String=""

) {



    var currentlyPopular: Boolean = false

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val titleId: Int? = null

    companion object {
        fun createFromResponse(response: TitleResponse, repository: GenreRepository): Title {
            val set = HashSet<Genre>()
            for (genre in response.results[0].materialData.genres ?: arrayListOf()) {
                set.add(Genre.getGenreByName(genre, repository))
            }
            return Title(response.results[0].title, Integer.valueOf(response.results[0].kinopoiskId), response.results[0].materialData.description
                    ?: "", response.results[0].materialData.posterUrl ?: "", set, response.results[0].link)
        }
    }
}
