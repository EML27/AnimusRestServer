package com.itis.restproject.server.controllers

import com.itis.restproject.server.dto.general.TitleDto
import com.itis.restproject.server.security.jwt.details.UserDetailsImpl
import com.itis.restproject.server.service.LikeService
import com.itis.restproject.server.service.TitlesService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("content")
class ContentController {


    @Autowired
    lateinit var titlesService: TitlesService

    @Autowired
    lateinit var likeService: LikeService

    @CrossOrigin("*")
    @GetMapping
    fun listOfAll(): List<TitleDto> {
        return titlesService.getAll()
    }

    @CrossOrigin("*")
    @PreAuthorize("isAuthenticated()")
    @GetMapping("recommended")
    fun listOfRecommendedForUserWithId(@RequestParam(required = false) size: Int?): List<TitleDto> {
        val userInfo = SecurityContextHolder.getContext().authentication.details as UserDetailsImpl
        val result = titlesService.getRecommendedTitlesForUserId(userInfo.userId)
        return if (size == null || size <= 0) {
            result.shuffled()
        } else {
            result.shuffled().take(size)
        }
    }

    @CrossOrigin("*")
    @GetMapping("popular")
    fun listOfPopular(@RequestParam(required = false) size: Int?): List<TitleDto> {
        val result = titlesService.getPopularTitles().shuffled()
        return if (size == null || size <= 0) {
            result
        } else {
            result.take(size)
        }

    }

    @CrossOrigin("*")
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("add/{kinopoiskId}")
    fun add(@PathVariable kinopoiskId: Int): String {
        try {
            titlesService.addTitleByKId(kinopoiskId)
            return "Success"
        } catch (e: Exception) {
            return "Error"
        }
    }

    @CrossOrigin("*")
    @GetMapping("likes/{kinopoiskId}")
    @PreAuthorize("isAuthenticated()")
    fun likes(@PathVariable kinopoiskId: Int): Boolean {
        val userInfo = SecurityContextHolder.getContext().authentication.details as UserDetailsImpl
        return likeService.isLikedByUser(userInfo.userId, kinopoiskId)
    }

    @CrossOrigin("*")
    @GetMapping("{kinopoiskId}")
    fun get(@PathVariable kinopoiskId: Int): ResponseEntity<TitleDto> {
        return ResponseEntity.ok(titlesService.getTitleByKId(kinopoiskId))
    }

    @CrossOrigin("*")
    @PostMapping("{kinopoiskId}")
    @PreAuthorize("isAuthenticated()")
    fun setLike(@PathVariable kinopoiskId: Int) {
        val userInfo = SecurityContextHolder.getContext().authentication.details as UserDetailsImpl
        likeService.setLike(userInfo.userId, kinopoiskId)

    }

    @CrossOrigin("*")
    @DeleteMapping("{kinopoiskId}")
    @PreAuthorize("isAuthenticated()")
    fun setUnLike(@PathVariable kinopoiskId: Int) {
        val userInfo = SecurityContextHolder.getContext().authentication.details as UserDetailsImpl
        likeService.setUnlike(userInfo.userId, kinopoiskId)

    }
}
