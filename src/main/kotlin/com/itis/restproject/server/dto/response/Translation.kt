package com.itis.restproject.server.dto.response


import com.fasterxml.jackson.annotation.JsonProperty

data class Translation(
    @JsonProperty("id")
    val id: Int,
    @JsonProperty("title")
    val title: String
)