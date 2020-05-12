package com.itis.restproject.server.service

import com.itis.restproject.server.model.Genre
import com.itis.restproject.server.repo.TitleRepository
import com.itis.restproject.server.repo.UserRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class LikeServiceImpl : LikeService {

    @Autowired
    lateinit var userRepository: UserRepository

    @Autowired
    lateinit var titleRepository: TitleRepository

    override fun setLike(userId: Int, titleKId: Int) {
        val user = userRepository.findUserByUserId(userId).get()
        val title = titleRepository.findTitleByKinopoiskId(titleKId).get()

        user.favouriteTitles = user.favouriteTitles.plus(title)
        userRepository.save(user)
        updateAllGenresInUser(userId)

    }

    override fun setUnlike(userId: Int, titleKId: Int) {

        val user = userRepository.findUserByUserId(userId).get()
        val title = titleRepository.findTitleByKinopoiskId(titleKId).get()

        user.favouriteTitles = user.favouriteTitles.minus(title)
        userRepository.save(user)
        updateAllGenresInUser(userId)

    }

    override fun updateAllGenresInUser(userId: Int) {
        val user = userRepository.findUserByUserId(userId).get()
        val newSet = HashSet<Genre>()
        for (title in user.favouriteTitles) {
            newSet.addAll(title.genresTable)
        }
        user.favouriteGenres = newSet
        userRepository.save(user)
    }

    override fun isLikedByUser(userId: Int, titleKId: Int): Boolean {
        val user = userRepository.findUserByUserId(userId).get()
        val title = titleRepository.findTitleByKinopoiskId(titleKId).get()

        return user.favouriteTitles.contains(title)
    }
}
