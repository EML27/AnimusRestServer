package com.itis.restproject.server.dto.response


import com.fasterxml.jackson.annotation.JsonProperty

data class TitleResponse(
    @JsonProperty("results")
    val results: List<Result>,
    @JsonProperty("time")
    val time: String,
    @JsonProperty("total")
    val total: Int
)