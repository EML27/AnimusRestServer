package com.itis.restproject.server.service

import com.itis.restproject.server.dto.general.TitleDto
import com.itis.restproject.server.model.Genre

interface TitlesService {
    fun getTitleByKId(kId: Int):TitleDto

    fun getTitlesByName(name: String): List<TitleDto>

    fun addTitleByKId(kId: Int)

    fun getTitlesByGenre(genre: Genre): List<TitleDto>

    fun getTitlesByGenre(genreName: String): List<TitleDto>

    fun getPopularTitles(): List<TitleDto>

    fun getAll(): List<TitleDto>

    fun getRecommendedTitlesForUserId(userId: Int):List<TitleDto>

    fun setPopular(kId: Int)

    fun setUnpopular(kId: Int)

    fun getIfPopular(kId: Int): Boolean
}
