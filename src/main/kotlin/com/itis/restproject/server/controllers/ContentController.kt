package com.itis.restproject.server.controllers

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.itis.restproject.server.dto.response.TitleResponse
import com.itis.restproject.server.model.Title
import com.itis.restproject.server.repo.GenreRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.*
import java.net.URL


@RestController
@RequestMapping("content")
class ContentController {

    @Value("\${kodikToken}")
    lateinit var kodikToken: String

    var repo = arrayListOf<Title>()

    @GetMapping
    fun listOfAll(): List<Title> {
        return repo
    }

    @GetMapping("recommended")
    fun listOfRecommendedForUserWithId(@RequestParam userId: Int): List<Title> {
        //TODO: make recommendations list
        return repo
    }

    @GetMapping("popular")
    fun listOfPopular(): List<Title> {
        //TODO: make popular logic
        return repo
    }

    @Autowired
    lateinit var repository: GenreRepository


    @GetMapping("add/{kinopoiskId}")
    fun add(@PathVariable kinopoiskId: Int): String {
        val mapper = ObjectMapper()
        try {
            val response = mapper.readValue<TitleResponse>(URL("https://kodikapi.com/search?token=${kodikToken}&kinopoisk_id=${kinopoiskId}&with_material_data=true"))
            val title = Title.createFromResponse(response, repository)
            repo.add(title)
        } catch (e: Exception) {
            e.printStackTrace()
            return "Error"
        }
        return "Success"
    }
}
