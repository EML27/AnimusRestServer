package com.itis.restproject.server.service

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.itis.restproject.server.dto.general.TitleDto
import com.itis.restproject.server.dto.response.TitleResponse
import com.itis.restproject.server.model.Genre
import com.itis.restproject.server.model.Title
import com.itis.restproject.server.repo.GenreRepository
import com.itis.restproject.server.repo.TitleRepository
import com.itis.restproject.server.repo.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.access.AccessDeniedException
import org.springframework.stereotype.Component
import java.net.URL

@Component
class TitlesServiceImpl : TitlesService {

    @Autowired
    lateinit var titleRepository: TitleRepository

    @Autowired
    lateinit var genreRepository: GenreRepository

    @Autowired
    lateinit var userRepository: UserRepository


    @Value("\${kodikToken}")
    lateinit var kodikToken: String

    override fun getTitleByKId(kId: Int): TitleDto {
        val title = titleRepository.findTitleByKinopoiskId(kId)
        if (title.isPresent) {
            return TitleDto.createFromTitle(title.get())
        } else throw AccessDeniedException("No title with such id found")
    }

    override fun getTitlesByName(name: String): List<TitleDto> {
        return titleRepository.findTitlesByNameContaining(name).map { title -> TitleDto.createFromTitle(title) }
    }

    override fun addTitleByKId(kId: Int) {
        val mapper = ObjectMapper()
        val response = mapper.readValue<TitleResponse>(URL("https://kodikapi.com/search?token=${kodikToken}&kinopoisk_id=${kId}&with_material_data=true"))
        val title = Title.createFromResponse(response, genreRepository)
        titleRepository.save(title)
    }

    override fun getTitlesByGenre(genre: Genre): List<TitleDto> {
        return genre.titles.toList().map { title -> TitleDto.createFromTitle(title) }
    }

    override fun getPopularTitles(): List<TitleDto> {
        return titleRepository.findByCurrentlyPopularIsTrue().map { title -> TitleDto.createFromTitle(title) }
    }

    override fun getTitlesByGenre(genreName: String): List<TitleDto> {
        return getTitlesByGenre(Genre.getGenreByName(genreName, genreRepository))
    }

    override fun getAll(): List<TitleDto> {
        return titleRepository.findAll().toList().map { title -> TitleDto.createFromTitle(title) }
    }

    override fun getRecommendedTitlesForUserId(userId: Int): List<TitleDto> {
        val user = userRepository.findUserByUserId(userId).get()
        val newSet = HashSet<Title>()
        for (genre in user.favouriteGenres) {
            newSet.addAll(genre.titles)
        }
        newSet.removeAll(user.favouriteTitles)
        newSet.addAll(titleRepository.findByCurrentlyPopularIsTrue())

        return newSet.toList().map { title -> TitleDto.createFromTitle(title) }
    }

    override fun setPopular(kId: Int) {
        val title = titleRepository.findTitleByKinopoiskId(kId).get()
        title.currentlyPopular = true
        titleRepository.save(title)
    }

    override fun setUnpopular(kId: Int) {
        val title = titleRepository.findTitleByKinopoiskId(kId).get()
        title.currentlyPopular = false
        titleRepository.save(title)
    }

    override fun getIfPopular(kId: Int): Boolean {
        return titleRepository.findTitleByKinopoiskId(kId).get().currentlyPopular
    }
}
