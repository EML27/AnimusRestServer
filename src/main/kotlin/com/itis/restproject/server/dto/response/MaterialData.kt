package com.itis.restproject.server.dto.response


import com.fasterxml.jackson.annotation.JsonProperty

data class MaterialData(
        @JsonProperty("actors")
        val actors: List<String>?,
        @JsonProperty("composers")
        val composers: List<String>?,
        @JsonProperty("countries")
        val countries: List<String>?,
        @JsonProperty("description")
        val description: String?,
        @JsonProperty("designers")
        val designers: List<String>?,
        @JsonProperty("directors")
        val directors: List<String>?,
        @JsonProperty("duration")
        val duration: Int?,
        @JsonProperty("editors")
        val editors: List<String>?,
        @JsonProperty("genres")
        val genres: List<String>?,
        @JsonProperty("imdb_rating")
        val imdbRating: Double?,
        @JsonProperty("imdb_votes")
        val imdbVotes: Int?,
        @JsonProperty("kinopoisk_rating")
        val kinopoiskRating: Double?,
        @JsonProperty("kinopoisk_votes")
        val kinopoiskVotes: Int?,
        @JsonProperty("operators")
        val operators: List<String>?,
        @JsonProperty("poster_url")
        val posterUrl: String?,
        @JsonProperty("premiere_world")
        val premiereWorld: String?,
        @JsonProperty("producers")
        val producers: List<String>?,
        @JsonProperty("title")
        val title: String?,
        @JsonProperty("title_en")
        val titleEn: String?,
        @JsonProperty("writers")
        val writers: List<String>?,
        @JsonProperty("year")
        val year: Int?,

        @JsonProperty("tagline")
        val tagline: String?,

        @JsonProperty("premiere_ru")
        val premiereRu: String?,

        @JsonProperty("minimal_age")
        val minimalAge: Int?
)