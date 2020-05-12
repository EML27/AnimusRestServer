package com.itis.restproject.server.service

interface LikeService {
    fun setLike(userId: Int, titleKId: Int)
    fun setUnlike(userId: Int, titleKId: Int)
    fun updateAllGenresInUser(userId: Int)
    fun isLikedByUser(userId: Int,titleKId: Int): Boolean
}
