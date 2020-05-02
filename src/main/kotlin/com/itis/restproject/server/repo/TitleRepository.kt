package com.itis.restproject.server.repo

import com.itis.restproject.server.model.Title
import org.springframework.data.jpa.repository.JpaRepository


interface TitleRepository: JpaRepository<Title, Int> {

}