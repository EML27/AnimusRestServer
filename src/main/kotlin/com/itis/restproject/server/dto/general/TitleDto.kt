package com.itis.restproject.server.dto.general

import com.itis.restproject.server.model.Title

data class TitleDto(val name: String,
                    val kinopoiskId: Int,
                    val description: String,
                    val posterSource: String,
                    val genres: String) {

    companion object {
        fun createFromTitle(title: Title): TitleDto = TitleDto(
                title.name,
                title.kinopoiskId,
                title.description,
                title.posterSource,
                title.genresTable.toString())

        fun createFromTitle(titles: List<Title>): List<TitleDto> {
            return titles.map { title -> createFromTitle(title) }
        }
    }
}