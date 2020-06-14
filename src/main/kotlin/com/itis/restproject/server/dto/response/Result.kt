package com.itis.restproject.server.dto.response


import com.fasterxml.jackson.annotation.JsonProperty

data class Result(
        @JsonProperty("blocked_countries")
        val blockedCountries: List<Any>,
        @JsonProperty("blocked_seasons")
        val blockedSeasons: BlockedSeasons,
        @JsonProperty("camrip")
        val camrip: Boolean,
        @JsonProperty("created_at")
        val createdAt: String,
        @JsonProperty("episodes_count")
        val episodesCount: Int,
        @JsonProperty("id")
        val id: String,
        @JsonProperty("imdb_id")
        val imdbId: String,
        @JsonProperty("kinopoisk_id")
        val kinopoiskId: String,
        @JsonProperty("last_episode")
        val lastEpisode: Int,
        @JsonProperty("last_season")
        val lastSeason: Int,
        @JsonProperty("link")
        val link: String,
        @JsonProperty("material_data")
        val materialData: MaterialData,
        @JsonProperty("other_title")
        val otherTitle: String,
        @JsonProperty("quality")
        val quality: String,
        @JsonProperty("title")
        val title: String,
        @JsonProperty("title_orig")
        val titleOrig: String,
        @JsonProperty("translation")
        val translation: Translation,
        @JsonProperty("type")
        val type: String,
        @JsonProperty("updated_at")
        val updatedAt: String,
        @JsonProperty("worldart_link")
        val worldartLink: String,
        @JsonProperty("year")
        val year: Int,

        @JsonProperty("shikimori_id")
        val shikimori_id: Int?
)
