package com.itis.restproject.server.model

import lombok.AllArgsConstructor
import lombok.Builder
import lombok.NoArgsConstructor
import javax.persistence.*

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user")
class User() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var userId: Int? = null

    @Column(unique = true)
    lateinit var email: String

    @Column(unique = true)
    lateinit var userName: String
    lateinit var passwordHash: String

    @Enumerated(value = EnumType.STRING)
    lateinit var role: Role

    var activationCode: String?=null

    var activated: Boolean = false

    @ManyToMany(cascade = [CascadeType.ALL])
    @JoinTable(name = "user_genre",
            joinColumns = [JoinColumn(name = "user_id")],
            inverseJoinColumns = [JoinColumn(name = "genre_id")])
    var favouriteGenres: Set<Genre> = HashSet()

    @ManyToMany(cascade = [CascadeType.ALL])
    @JoinTable(name = "user_title",
            joinColumns = [JoinColumn(name = "user_id")],
            inverseJoinColumns = [JoinColumn(name = "title_id")])
    var favouriteTitles: Set<Title> = HashSet()

}




